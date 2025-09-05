package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.model.*;
import com.chattop.oc_p3.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@Tag(name = "Rental")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping()
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public RentalsResponse getAllRentals() {
        List<RentalDto> rentals = rentalService.getAllRentals();
        return new RentalsResponse(rentals);
    }

    @GetMapping("/{id}")
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public RentalDto getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<ApiResponse> createRental(
            @Valid @ModelAttribute RentalToCreate rentalToCreate,
            @AuthenticationPrincipal Jwt jwt
    ) {
        rentalService.create(rentalToCreate, jwt);
        return ResponseEntity.ok(new ApiResponse("Rental created"));
    }

    @PutMapping(value = "/{id}")
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<ApiResponse> updateRental(@PathVariable Long id, @Valid @ModelAttribute RentalToEdit rentalToEdit) {
        rentalService.update(id, rentalToEdit);
        return ResponseEntity.ok(new ApiResponse("Rental updated"));
    }

}
