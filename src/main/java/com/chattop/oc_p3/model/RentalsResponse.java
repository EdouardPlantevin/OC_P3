package com.chattop.oc_p3.model;

import java.util.List;

public record RentalsResponse(
        List<RentalDto> rentals
) {
}
