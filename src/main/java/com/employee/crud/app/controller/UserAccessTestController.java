package com.employee.crud.app.controller;

import com.employee.crud.app.model.roles.UserEnum;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/access")
@RestController
public class UserAccessTestController {

    @GetMapping("/index")
    public String publicContent(){
        return "Public Content - This is the landing page of this website!";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public String userAccess(){
        return "User Level Access Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public String adminAccess(){
        return "Admin Level Access Content.";
    }
    @GetMapping("/superadmin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String superAdminAccess(){
        return "Super Admin Level Access Content.";
    }
}
