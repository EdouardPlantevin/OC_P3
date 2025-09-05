package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.model.*;
import com.chattop.oc_p3.security.JwtService;
import com.chattop.oc_p3.service.UserService;
import com.chattop.oc_p3.service.exception.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Account")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(
            @RequestBody @Valid UserRegister userRegister,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        userService.saveUser(userRegister);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userRegister.email(), userRegister.password());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        securityContextRepository.saveContext(securityContext, request, response);

        String jwt = jwtService.createToken(authentication);

        return ResponseEntity.ok(new TokenResponse(jwt));
    }

    //TODO: add login in feature/jwt
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @RequestBody @Valid UserLogin userLogin,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.login(), userLogin.password());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        securityContextRepository.saveContext(securityContext, request, response);

        String jwt = jwtService.createToken(authentication);

        return ResponseEntity.ok(new TokenResponse(jwt));
    }


    @GetMapping("/me")
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public UserDto getUser(@AuthenticationPrincipal Jwt jwt) {
        String email = jwt.getSubject();
        return userService.findUserByEmail(email);
    }

}
