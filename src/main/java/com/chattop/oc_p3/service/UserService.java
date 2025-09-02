package com.chattop.oc_p3.service;

import com.chattop.oc_p3.entity.User;
import com.chattop.oc_p3.model.UserDto;
import com.chattop.oc_p3.model.UserRegister;
import com.chattop.oc_p3.repository.UserRepository;
import com.chattop.oc_p3.service.exception.EmailAlreadyExist;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public void saveUser(UserRegister userRegister) {

        if (isEmailExists(userRegister.email())) {
            throw new EmailAlreadyExist(userRegister.email());
        }

        //TODO: encrypt password in feature/jwt

        User user = new User();
        user.setEmail(userRegister.email());
        user.setName(userRegister.name());
        user.setPassword(userRegister.password());
        user.setCreatedAt(new java.util.Date());
        user.setUpdatedAt(new java.util.Date());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    private boolean isEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }

}
