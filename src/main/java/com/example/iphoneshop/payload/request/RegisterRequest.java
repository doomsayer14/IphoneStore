package com.example.iphoneshop.payload.request;

import com.example.iphoneshop.annotation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
@PasswordMatches
public class RegisterRequest {

    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    private String email;
    @NotEmpty(message = "Please enter your name")
    private String firstname;
    @NotEmpty(message = "Please enter your lastname")
    private String lastname;
    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    private String confirmPassword;


}
