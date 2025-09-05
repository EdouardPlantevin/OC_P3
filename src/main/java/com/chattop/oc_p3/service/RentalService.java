package com.chattop.oc_p3.service;

import com.chattop.oc_p3.entity.AppUser;
import com.chattop.oc_p3.entity.Rental;
import com.chattop.oc_p3.model.RentalDto;
import com.chattop.oc_p3.model.RentalToCreate;
import com.chattop.oc_p3.model.RentalToEdit;
import com.chattop.oc_p3.repository.RentalRepository;
import com.chattop.oc_p3.repository.UserRepository;
import com.chattop.oc_p3.service.exception.RentalNotFoundException;
import com.chattop.oc_p3.service.exception.UserNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public List<RentalDto> getAllRentals() {
        return rentalRepository.findAll()
                .stream()
                .map(RentalMapper::toDto)
                .toList();
    }

    public RentalDto getRentalById(Long id) {
        return RentalMapper.toDto(
                rentalRepository
                        .findById(id)
                        .orElseThrow(() -> new RentalNotFoundException(id))
        );
    }

    public void create(@Valid RentalToCreate rentalToCreate, Jwt jwt) {

        Optional<AppUser> user = userRepository.findByEmail(jwt.getSubject());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        String pictureName;
        try {
            pictureName = uploadFile(rentalToCreate.picture());
        } catch (Exception e) {
            throw new RuntimeException();
        }

        Rental rental = new Rental();
        rental.setName(rentalToCreate.name());
        rental.setSurface(rentalToCreate.surface());
        rental.setPrice(rentalToCreate.price());
        rental.setPicture(pictureName);
        rental.setDescription(rentalToCreate.description());
        rental.setCreatedAt(new java.util.Date());
        rental.setUpdatedAt(new java.util.Date());
        rental.setOwner(userRepository.findById(user.get().getId()).orElseThrow());
        rentalRepository.save(rental);
    }

    private String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        String safeFileName = UUID.randomUUID() + "_" +
                StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Path uploadPath = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create upload directory", e);
        }

        Path targetPath = uploadPath.resolve(safeFileName).normalize();

        if (!targetPath.startsWith(uploadPath)) {
            throw new RuntimeException("Invalid file path, possible path traversal attempt");
        }

        try {
            file.transferTo(targetPath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save file", e);
        }

        return safeFileName;
    }


    public void update(Long id, @Valid RentalToEdit rentalToEdit) {
        Rental rental = rentalRepository
                .findById(id)
                .orElseThrow(() -> new RentalNotFoundException(id));

        rental.setName(rentalToEdit.name());
        rental.setSurface(rentalToEdit.surface());
        rental.setPrice(rentalToEdit.price());
        rental.setDescription(rentalToEdit.description());
        rental.setUpdatedAt(new java.util.Date());

        try {
            rentalRepository.save(rental);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

