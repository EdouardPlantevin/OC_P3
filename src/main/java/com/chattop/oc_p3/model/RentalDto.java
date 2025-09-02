package com.chattop.oc_p3.model;

import java.util.Date;

import jakarta.validation.constraints.*;


public record RentalDto(

        @NotBlank()
        Long id,

        @NotBlank()
        @Size(max = 255)
        String name,

        @NotNull()
        @Positive()
        Integer surface,

        @NotNull()
        @Positive()
        Integer price,

        @NotBlank()
        @Size(max = 500)
        @Pattern(regexp = "^(http|https)://.*$")
        String picture,

        @NotBlank()
        @Size(min = 10, max = 1000)
        String description,

        @NotNull()
        Long owner_id,

        @PastOrPresent()
        Date created_at,

        @PastOrPresent()
        Date updated_at
) {}

