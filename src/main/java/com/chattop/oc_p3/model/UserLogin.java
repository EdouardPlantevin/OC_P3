package com.chattop.oc_p3.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLogin(
        @NotBlank(message = "email is mandatory")
        @Email(message = "email is invalid")
        String login, //email

        @NotBlank(message = "password is mandatory")
        String password
) {
}
