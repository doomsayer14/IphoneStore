package com.example.iphoneshop.annotation;

import com.example.iphoneshop.validation.PasswordMatchesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "Password do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
