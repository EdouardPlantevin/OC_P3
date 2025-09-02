package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.model.MessageResponse;
import com.chattop.oc_p3.model.RentalDto;
import com.chattop.oc_p3.model.RentalToCreate;
import com.chattop.oc_p3.model.RentalToEdit;
import com.chattop.oc_p3.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;


    @GetMapping()
    public List<RentalDto> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public RentalDto getRentalById(@PathVariable Long id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<MessageResponse> createRental(@Valid @ModelAttribute RentalToCreate rentalToCreate) {
        rentalService.create(rentalToCreate);
        return ResponseEntity.ok(new MessageResponse("Rental created"));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MessageResponse> updateRental(@PathVariable Long id, @Valid @ModelAttribute RentalToEdit rentalToEdit) {
        rentalService.update(id, rentalToEdit);
        return ResponseEntity.ok(new MessageResponse("Rental updated"));
    }



}
