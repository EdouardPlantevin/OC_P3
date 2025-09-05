package com.chattop.oc_p3.service;

import com.chattop.oc_p3.entity.Rental;
import com.chattop.oc_p3.model.RentalDto;

public class RentalMapper {
    public static RentalDto toDto(Rental rental) {

        String pictureUrl = rental.getPicture() != null
                ? "http://localhost:3001/images/" + rental.getPicture()
                : null;

        return new RentalDto(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                pictureUrl,
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }
}
