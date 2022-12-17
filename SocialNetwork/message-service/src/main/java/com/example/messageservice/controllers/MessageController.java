package com.example.messageservice.controllers;

import com.example.messageservice.dto.MessageDto;
import com.example.messageservice.services.MessageRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRestService messageRestService;
    @RequestMapping(method = RequestMethod.GET, value = "/{message}")
    MessageDto getMessage(@PathVariable(name = "message") String message) {
        return messageRestService.getMessage(message);
    }
}
