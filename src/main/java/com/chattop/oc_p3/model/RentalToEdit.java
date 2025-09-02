package com.chattop.oc_p3.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RentalToEdit(
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
        @Size(min = 10, max = 1000)
        String description
) {
}
