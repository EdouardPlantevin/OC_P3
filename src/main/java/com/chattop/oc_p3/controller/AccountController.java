package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.model.UserDto;
import com.chattop.oc_p3.model.UserLogin;
import com.chattop.oc_p3.model.UserRegister;
import com.chattop.oc_p3.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid UserRegister userRegister) {
        userService.saveUser(userRegister);
        return "jwt";
    }

    //TODO: add login in feature/jwt
    @PostMapping("/login")
    public String login(@RequestBody @Valid UserLogin userLogin) {
        return "jwt";
    }

    //TODO: remove id in feature/jwt
    @GetMapping("/me/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

}
