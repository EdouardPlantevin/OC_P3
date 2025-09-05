package com.chattop.oc_p3.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegister(
        @NotBlank(message = "email is mandatory")
        @Email(message = "email is invalid")
        String email,

        @NotBlank(message = "name is mandatory")
        String name,

        @NotBlank(message = "password is mandatory")
        String password
) {
}
