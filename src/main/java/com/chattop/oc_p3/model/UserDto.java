package com.chattop.oc_p3.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
        @JsonProperty("created_at")
        Date createdAt,

        @NotBlank
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
        @JsonProperty("updated_at")
        Date updatedAt
) {
}
