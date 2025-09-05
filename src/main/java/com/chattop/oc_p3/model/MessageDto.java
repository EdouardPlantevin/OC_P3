package com.chattop.oc_p3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageDto(
        @NotBlank
        String message,

        @NotNull
        @JsonProperty("rental_id")
        Long rentalId
) {
}
