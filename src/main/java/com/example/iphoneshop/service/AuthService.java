package com.example.iphoneshop.service;

import com.example.iphoneshop.entity.User;
import com.example.iphoneshop.payload.request.LoginRequest;
import com.example.iphoneshop.payload.request.RegisterRequest;
import com.example.iphoneshop.payload.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    public User register(RegisterRequest registerRequest) {
        userService.createUser(registerRequest);
        return userService.createUser(registerRequest);
    }

    public MessageResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("User logged in, username: {}", loginRequest.getUsername());
        return new MessageResponse("User logged in successfully.");
    }
}
