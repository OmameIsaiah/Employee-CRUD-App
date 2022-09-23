package com.employee.crud.app.controller;

import com.employee.crud.app.exception.*;
import com.employee.crud.app.model.User;
import com.employee.crud.app.model.roles.UserEnum;
import com.employee.crud.app.model.roles.UserRole;
import com.employee.crud.app.payload.request.UserSigninRequest;
import com.employee.crud.app.payload.request.UserSignupRequest;
import com.employee.crud.app.payload.response.JwtAuthResponse;
import com.employee.crud.app.payload.response.JwtResponse;
import com.employee.crud.app.payload.response.UserSigninResponse;
import com.employee.crud.app.payload.response.UserSignupResponse;
import com.employee.crud.app.repository.RoleRepository;
import com.employee.crud.app.service.JwtTokenProvider;
import com.employee.crud.app.service.UserDetailsImpl;
import com.employee.crud.app.service.UserService;
import com.employee.crud.app.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class UserAccessController {

    @Autowired
    public UserService userService;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public JwtTokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> userSignup(@Valid @RequestBody UserSignupRequest signupRequest){

        Set<String> strRoles = signupRequest.getRoles();
        Set<UserRole> roles = new HashSet<>();
        if (strRoles == null) {
            UserRole userRole = roleRepository.findByName(UserEnum.ROLE_USER)
                    .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "superadmin":
                        UserRole superAdminRole = roleRepository.findByName(UserEnum.ROLE_SUPER_ADMIN)
                                .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found for superadmin."));
                        roles.add(superAdminRole);
                        break;
                    case "admin":
                        UserRole adminRole = roleRepository.findByName(UserEnum.ROLE_ADMIN)
                                .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found for admin."));
                        roles.add(adminRole);
                        break;
                    default:
                        UserRole userRole = roleRepository.findByName(UserEnum.ROLE_USER)
                                .orElseThrow(() -> new RoleNotFoundException("Error: Role is not found for user."));
                        roles.add(userRole);
                }
            });
        }
        //user.setRoles(roles);

        SimpleDateFormat date= new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat time= new SimpleDateFormat("hh:mm:ss a");
        Date d1= new Date();
        Date d2= new Date();
        String currentDate= date.format(d1);
        String currentTime = time.format(d2);
        String dateCreated = "Date of creation: "+ currentDate + " at "+ currentTime;


        User user = User
                .builder()
                .name(signupRequest.getName())
                .username(signupRequest.getEmail())
                .password(signupRequest.getPassword())
                .email(signupRequest.getEmail())
                .phoneNumber(signupRequest.getPhoneNumber())
                .picture(signupRequest.getPictureURL())
                .roles(roles)
                .status(dateCreated)
                .build();

        try {
            userService.saveUser(user);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException | PhoneNoAlreadyExistsException e) {
            throw new BadRequestException(e.getMessage());
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/signup/{name}")
                .buildAndExpand(user.getName()).toUri();

        UserSignupResponse response= new UserSignupResponse();
        response.setSuccess(true);
        response.setMessage("User registered successfully");
        response.setData(user);

        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> userSignin(@Valid @RequestBody UserSigninRequest signinRequest){

        //String token = userService.login(signinRequest.getEmail(),signinRequest.getPassword());
        //return ResponseEntity.ok(new JwtAuthResponse(token));

        Authentication authentication = userService.signIn(signinRequest.getEmail(), signinRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        log.info("JWT Token: {}",jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getName(),
                userDetails.getPhoneNumber(),
                userDetails.getPicture(),
                userDetails.getLastLogin(),
                userDetails.getStatus(),
                roles));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> userSignout(){
        ResponseCookie cookie = tokenProvider.getCleanJwtCookie();
        UserSigninResponse out = new UserSigninResponse();
        out.setSuccess(true);
        out.setMessage("You've been signed out!");
        out.setData(null);
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(out);

    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteEmployeeByID(@PathVariable("id") Long id){
        User u= userService.findUserByID(id);
        userService.deleteUserByID(id);
        UserSigninResponse out = new UserSigninResponse();
        out.setSuccess(true);
        out.setMessage("User have been deleted successfully!");
        out.setData(u);
        return ResponseEntity.ok().body(out);
    }
}
