package com.employee.crud.app.service;


import com.employee.crud.app.exception.EmailAlreadyExistsException;
import com.employee.crud.app.exception.PhoneNoAlreadyExistsException;
import com.employee.crud.app.exception.UsernameAlreadyExistsException;
import com.employee.crud.app.model.User;
import com.employee.crud.app.repository.RoleRepository;
import com.employee.crud.app.repository.UserRepository;
import com.employee.crud.app.security.PasswordUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public User saveUser(User user) {
        log.info("registering user {}, {}", user.getName(), user.getUsername());

        if(userRepository.existsByUsername(user.getUsername())) {
            log.warn("username {} already exists.", user.getUsername());
            throw new UsernameAlreadyExistsException(
                    String.format("username %s already exists", user.getUsername()));
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            log.warn("email {} already exists.", user.getEmail());
            throw new EmailAlreadyExistsException(
                    String.format("email %s already exists", user.getEmail()));
        }
        if(userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            log.warn("phone number {} already exists.", user.getPhoneNumber());
            throw new PhoneNoAlreadyExistsException(
                    String.format("phone number %s already exists", user.getPhoneNumber()));
        }

        /////////////*********************///////////////////
        /////////////*********************///////////////////
        /*
        String password = user.getPassword();
        String passwordSalt = PasswordUtils.getSalt(25);
        // Protect user's password. The generated value can be stored in DB.
        String mySecurePassword = PasswordUtils.generateSecurePassword(password, passwordSalt);
        user.setPassword(mySecurePassword);
        user.setPasswordSalt(passwordSalt);
        */
        /////////////*********************///////////////////
        /////////////*********************///////////////////

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //user.setRoles(new HashSet<>() {{add(role);}});

        log.info("user {} was registered successfully!", user.getName());
        return userRepository.save(user);
    }

    @Override
    public User findUserByID(Long id) {
        log.info("retrieving user {}", id);
        return userRepository.findById(id).get();
    }

    @Override
    public User findUserByName(String name) {
        log.info("retrieving user {}", name);
        User searchUser= null;
        List<User> allUsers = userRepository.findAll();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getName().equals(name)){
                searchUser= allUsers.get(i);
                break;
            }
        }
        return searchUser;
    }

    @Override
    public User findUserByEmail(String email) {
        log.info("retrieving user {}", email);
        User searchUser= null;
        List<User> allUsers = userRepository.findAll();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getEmail().equals(email)){
                searchUser= allUsers.get(i);
                break;
            }
        }
        return searchUser;
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        log.info("retrieving user {}", phoneNumber);
        User searchUser= null;
        List<User> allUsers = userRepository.findAll();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getPhoneNumber().equals(phoneNumber)){
                searchUser= allUsers.get(i);
                break;
            }
        }
        return searchUser;
    }

    @Override
    public void deleteUserByID(Long id) {
        log.info("deleting user {}", id);
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<User> findAllUser() {
        log.info("retrieving all users");
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        log.info("updating user {}", user.getName());
        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        return tokenProvider.generateToken(authentication);
    }

    @Override
    public Authentication signIn(String email, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        //return tokenProvider.generateToken(authentication);
        return authentication;
    }

    @Override
    public User findByUsername(String username) {
        log.info("retrieving user {}", username);
        User searchUser= null;
        List<User> allUsers = userRepository.findAll();
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getUsername().equals(username)){
                searchUser= allUsers.get(i);
                break;
            }
        }
        return searchUser;
        //return userRepository.findByUsername(username).get();
    }

    public Optional<User> findById(Long id) {
        log.info("retrieving user {}", id);
        return userRepository.findById(id);
    }
}
