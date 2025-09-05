package com.chattop.oc_p3.service;

import com.chattop.oc_p3.entity.AppUser;
import com.chattop.oc_p3.entity.Message;
import com.chattop.oc_p3.entity.Rental;
import com.chattop.oc_p3.model.MessageDto;
import com.chattop.oc_p3.repository.MessageRepository;
import com.chattop.oc_p3.repository.RentalRepository;
import com.chattop.oc_p3.repository.UserRepository;
import com.chattop.oc_p3.service.exception.RentalNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public void create(@Valid MessageDto messageDto, Jwt jwt) {

        Rental rental = rentalRepository
                .findById(messageDto.rentalId())
                .orElseThrow(() -> new RentalNotFoundException(messageDto.rentalId()));

        String email = jwt.getSubject();

        Optional<AppUser> appUser = userRepository.findByEmail(email);
        if (appUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Message message = new Message();
        message.setMessage(messageDto.message());
        message.setRental(rental);
        message.setAppUser(appUser.get());

        try {
            messageRepository.save(message);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
