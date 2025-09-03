package com.chattop.oc_p3.controller;

import com.chattop.oc_p3.model.ApiResponse;
import com.chattop.oc_p3.model.MessageDto;
import com.chattop.oc_p3.service.MessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Message")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping()
    public ResponseEntity<ApiResponse> createMessage(@RequestBody @Valid MessageDto messageDto) {
        messageService.create(messageDto);
        return ResponseEntity.ok(new ApiResponse("Message send with success"));
    }


}
