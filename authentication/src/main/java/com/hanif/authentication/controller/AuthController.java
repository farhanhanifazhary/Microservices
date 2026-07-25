package com.hanif.authentication.controller;

import com.hanif.authentication.dto.AuthRequest;
import com.hanif.authentication.dto.AuthResponse;
import com.hanif.authentication.entity.User;
import com.hanif.authentication.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody AuthRequest request) {
        log.info("START - register");
        var user = authService.register(request.getUsername(), request.getPassword());
        log.info("END - register");
        return user;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        log.info("START - login");
        String token = authService.login(
            request.getUsername(),
            request.getPassword()
        );

        log.info("END - login");
        return new AuthResponse(token);
    }
}
