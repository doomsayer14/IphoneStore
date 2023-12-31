package com.example.iphoneshop.service;

import com.example.iphoneshop.entity.User;
import com.example.iphoneshop.entity.enums.Role;
import com.example.iphoneshop.exception.UserExistException;
import com.example.iphoneshop.payload.request.RegisterRequest;
import com.example.iphoneshop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User createUser(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .username(registerRequest.getUsername())
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .role(Role.CLIENT)
                .build();
        try {
            log.info("Saving user {}", registerRequest.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials.");
        }
    }
}