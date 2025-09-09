package com.chattop.oc_p3.service;

import com.chattop.oc_p3.entity.AppUser;
import com.chattop.oc_p3.model.UserDto;
import com.chattop.oc_p3.model.UserRegister;
import com.chattop.oc_p3.repository.UserRepository;
import com.chattop.oc_p3.service.exception.EmailAlreadyExistException;
import com.chattop.oc_p3.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto findUserById(Long id) {
        AppUser appUser = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new UserDto(
                appUser.getId(),
                appUser.getName(),
                appUser.getEmail(),
                appUser.getCreatedAt(),
                appUser.getUpdatedAt()
        );
    }

    public UserDto findUserByEmail(String email) {
        AppUser appUser = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new UserDto(
                appUser.getId(),
                appUser.getName(),
                appUser.getEmail(),
                appUser.getCreatedAt(),
                appUser.getUpdatedAt()
        );
    }

    public void saveUser(UserRegister userRegister) {

        if (isEmailExists(userRegister.email())) {
            throw new EmailAlreadyExistException(userRegister.email());
        }

        AppUser appUser = new AppUser();
        appUser.setEmail(userRegister.email());
        appUser.setName(userRegister.name());
        appUser.setPassword(passwordEncoder.encode(userRegister.password()));
        appUser.setCreatedAt(new java.util.Date());
        appUser.setUpdatedAt(new java.util.Date());

        userRepository.save(appUser);
    }

    private boolean isEmailExists(String email) {
        Optional<AppUser> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

}
