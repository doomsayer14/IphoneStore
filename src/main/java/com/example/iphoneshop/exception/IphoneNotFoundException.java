package com.example.iphoneshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IphoneNotFoundException extends RuntimeException {
    public IphoneNotFoundException(String message) {
        super(message);
    }
}
