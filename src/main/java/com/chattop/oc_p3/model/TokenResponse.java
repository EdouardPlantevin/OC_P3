package com.chattop.oc_p3.model;

import jakarta.validation.constraints.NotBlank;

public record TokenResponse(
        @NotBlank
        String token
) {
}
