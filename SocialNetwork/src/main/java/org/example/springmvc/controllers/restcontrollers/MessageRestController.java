package org.example.springmvc.controllers.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.client.dto.MessageDto;
import org.example.springmvc.service.MessageRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageRestController {
    private final MessageRestService messageRestService;
    @GetMapping("/{message}")
    public ResponseEntity<?> getMessage(@PathVariable final MessageDto message) {
        return ResponseEntity.ok(messageRestService.getMessage(message));
    }
}