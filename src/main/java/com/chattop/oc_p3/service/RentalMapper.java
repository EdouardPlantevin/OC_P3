package com.chattop.oc_p3.service;

import com.chattop.oc_p3.entity.Rental;
import com.chattop.oc_p3.model.RentalDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {

    @Value("${app.path-image-url}")
    private String pathImageUrl;

    public RentalDto toDto(Rental rental) {

        String pictureUrl = rental.getPicture() != null
                ? pathImageUrl + rental.getPicture()
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
