package com.chattop.oc_p3.service;

import com.chattop.oc_p3.entity.Message;
import com.chattop.oc_p3.entity.Rental;
import com.chattop.oc_p3.entity.User;
import com.chattop.oc_p3.model.MessageDto;
import com.chattop.oc_p3.repository.MessageRepository;
import com.chattop.oc_p3.repository.RentalRepository;
import com.chattop.oc_p3.repository.UserRepository;
import com.chattop.oc_p3.service.exception.RentalNotFoundException;
import com.chattop.oc_p3.service.exception.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public void create(@Valid MessageDto messageDto) {

        Rental rental = rentalRepository
                .findById(messageDto.rentalId())
                .orElseThrow(() -> new RentalNotFoundException(messageDto.rentalId()));

        User user = userRepository
                .findById(messageDto.userId())
                .orElseThrow(() -> new UserNotFoundException(messageDto.userId()));

        Message message = new Message();
        message.setMessage(messageDto.message());
        message.setRental(rental);
        message.setUser(user);

        try {
            messageRepository.save(message);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
