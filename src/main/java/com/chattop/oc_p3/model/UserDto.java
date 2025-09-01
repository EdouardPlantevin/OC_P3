package com.chattop.oc_p3.model;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UserDto(
        @NotBlank
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank
        Date createdAt,

        @NotBlank
        Date updatedAt
) {
}
