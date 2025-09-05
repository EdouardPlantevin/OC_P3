package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.model.UserDto;
import com.chattop.oc_p3.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class UserController {

    private final UserService userService;
    private final JwtDecoder jwtDecoder;

    @GetMapping("/{id}")
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

}
