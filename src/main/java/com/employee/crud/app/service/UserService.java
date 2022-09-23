package com.employee.crud.app.service;

import com.employee.crud.app.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User saveUser(User e);

    User findUserByID(Long id);

    User findUserByName(String name);

    User findByUsername(String username);
    User findUserByEmail(String email);

    User findUserByPhoneNumber(String phoneNumber);
    void deleteUserByID(Long id);

    List<User> findAllUser();

    User update(User user);

    String login(String email, String password);

    Authentication signIn(String email, String password);
}
