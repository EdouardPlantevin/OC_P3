package com.chattop.oc_p3.service;

import com.chattop.oc_p3.entity.Rental;
import com.chattop.oc_p3.model.RentalDto;

public class RentalMapper {
    public static RentalDto toDto(Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }
}
