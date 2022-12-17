package com.example.messageservice.controllers;

import com.example.messageservice.dto.MessageDto;
import com.example.messageservice.services.MessageRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRestService messageRestService;
    @GetMapping("/{message}")
    public ResponseEntity<?> getMessage(@PathVariable final MessageDto message) {
        return ResponseEntity.ok(messageRestService.getMessage(message.getText()));
    }
}
