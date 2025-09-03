package com.chattop.oc_p3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLogin(
        @NotBlank(message = "email is mandatory")
        @Email(message = "email is invalid")
        @JsonProperty("email")
        String login, //email

        @NotBlank(message = "password is mandatory")
        String password
) {
}
