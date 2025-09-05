package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.model.ApiResponse;
import com.chattop.oc_p3.model.MessageDto;
import com.chattop.oc_p3.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Message")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class MessageController {

    private final MessageService messageService;

    @PostMapping()
    @Operation(security = {@SecurityRequirement(name = "bearerAuth")})
    public ResponseEntity<ApiResponse> createMessage(
            @RequestBody @Valid MessageDto messageDto,
            @AuthenticationPrincipal Jwt jwt
    ) {
        messageService.create(messageDto, jwt);
        return ResponseEntity.ok(new ApiResponse("Message send with success"));
    }


}
