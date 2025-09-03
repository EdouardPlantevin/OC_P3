package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.model.*;
import com.chattop.oc_p3.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Account")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody @Valid UserRegister userRegister) {
        userService.saveUser(userRegister);
        return ResponseEntity.ok(new TokenResponse("jwt"));
    }

    //TODO: add login in feature/jwt
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserLogin userLogin) {
        return ResponseEntity.ok(new TokenResponse("jwt"));
    }

    //TODO: remove id in feature/jwt
    @GetMapping("/me")
    public UserDto getUser() {
        return userService.findUserById(1L);
    }

}
