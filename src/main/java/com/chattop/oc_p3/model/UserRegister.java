package com.chattop.oc_p3.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegister(
        @NotBlank(message = "Email is mandatory")
        @Email(message = "email is invalid")
        String email,

        @NotBlank(message = "name is mandatory")
        String name,

        @NotBlank(message = "password is mandatory")
        String password
) {
}
