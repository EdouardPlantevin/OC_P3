package com.chattop.oc_p3.model;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record RentalToCreate(
        @NotBlank()
        @Size(max = 255)
        String name,

        @NotNull()
        @Positive()
        Integer surface,

        @NotNull()
        @Positive()
        Integer price,

        @NotNull()
        MultipartFile picture,

        @NotBlank()
        @Size(min = 10, max = 1000)
        String description

) {
}
