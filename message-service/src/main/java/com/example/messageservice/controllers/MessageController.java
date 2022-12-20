package com.example.messageservice.controllers;

import com.example.messageservice.converter.MessageConverter;
import com.example.messageservice.dto.UserMessagesDto;
import com.example.messageservice.model.Message;
import com.example.messageservice.services.MessageRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageRestService messageRestService;

    private final MessageConverter messageConverter;

    @RequestMapping(method = RequestMethod.GET, value = "/{friendsId}")
    UserMessagesDto getUserMessages(@PathVariable(name = "friendsId") long friendsId) {
        List<Message> userMessages = messageRestService.getUserMessages(friendsId);
        UserMessagesDto userMessagesDto = new UserMessagesDto();
        if (userMessages != null) {
            userMessagesDto.setUserMessages(
                    userMessages
                            .stream()
                            .map(messageConverter::messageToDto)
                            .toList());
        }
        return userMessagesDto;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserMessagesDto sendMessage(@RequestBody final Message message) {
        messageRestService.saveMessage(message);
        return getUserMessages(message.getFriends().getId());
    }
}
