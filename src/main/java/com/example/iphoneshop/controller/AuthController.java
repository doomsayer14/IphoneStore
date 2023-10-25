package com.example.iphoneshop.controller;

import com.example.iphoneshop.payload.request.LoginRequest;
import com.example.iphoneshop.payload.request.RegisterRequest;
import com.example.iphoneshop.service.AuthService;
import com.example.iphoneshop.validation.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ResponseErrorValidation responseErrorValidation;

    @PostMapping({"/register", "/sign-up"})
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest,
                                           BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping({"/login", "sign-in"})
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest,
                                        BindingResult bindingResult) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) {
            return errors;
        }
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
