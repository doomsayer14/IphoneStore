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
        User user = new User();
        user.setEmail(user.getEmail());
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.CLIENT);

        try {
            log.info("Saving user {}", registerRequest.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            log.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials.");
        }
    }
}
