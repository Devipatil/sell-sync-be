package com.sellSync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sellSync.entity.UserLoginData;
import com.sellSync.entity.Users;
import com.sellSync.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping("/signUp")
    public String signUp(@RequestBody Users user) {
        String username = user.getUsername();
        if (service.getUser(username) == null) {
            service.signUp(user);
            return "User created successfully!";
        } else {
            return "Username already exists!";
        }
    }

    @PostMapping("/signIn")
    public String signIn(
            @RequestBody UserLoginData user,
            HttpServletRequest request
    ) {
        String username = user.getUsername();
        String password = user.getPassword();

        // 1) ensure user exists
        Users u = service.getUser(username);
        if (u == null) {
            return "Username does not exist!";
        }

        // 2) verify password
        if (!service.validate(username, password)) {
            return "wrong password";
        }

        // 3) grant ROLE_ADMIN or ROLE_CUSTOMER
        List<SimpleGrantedAuthority> authorities = "admin".equals(u.getRole())
            ? List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
            : List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));

        // 4) build Authentication
        Authentication auth = new UsernamePasswordAuthenticationToken(
            username,
            null,
            authorities
        );

        // 5) store in SecurityContext
        SecurityContextHolder.getContext().setAuthentication(auth);

        // 6) persist SecurityContext to session
        request.getSession(true).setAttribute(
            HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
            SecurityContextHolder.getContext()
        );

        // 7) return role for front-end routing
        return u.getRole();
    }
}
