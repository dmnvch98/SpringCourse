package com.example.messageservice.controllers;

import com.example.messageservice.dto.UserMessagesDto;
import com.example.messageservice.model.Message;
import com.example.messageservice.services.MessageRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRestService messageRestService;
    @RequestMapping(method = RequestMethod.GET, value = "/{friendsId}")
    UserMessagesDto getUserMessages(@PathVariable(name = "friendsId") long friendsId) {
        return messageRestService.getUserMessages(friendsId);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserMessagesDto sendMessage(@RequestBody final Message message) {
        return messageRestService.saveMessage(message);
    }
}
