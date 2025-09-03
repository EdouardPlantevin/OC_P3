package com.chattop.oc_p3.model;

import jakarta.validation.constraints.NotBlank;

public record ApiResponse(
        @NotBlank
        String message
) {
}
