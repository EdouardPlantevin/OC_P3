package com.chattop.oc_p3.service;

import com.chattop.oc_p3.entity.AppUser;
import com.chattop.oc_p3.model.UserDto;
import com.chattop.oc_p3.model.UserRegister;
import com.chattop.oc_p3.repository.UserRepository;
import com.chattop.oc_p3.service.exception.EmailAlreadyExist;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto findUserById(Long id) {
        AppUser appUser = userRepository.findById(id).orElseThrow();

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
            throw new EmailAlreadyExist(userRegister.email());
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
