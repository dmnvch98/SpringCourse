package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.client.MessageClient;
import org.example.springmvc.client.dto.MessageDto;
import org.example.springmvc.client.dto.UserMessagesDto;
import org.example.springmvc.model.Friends;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageRestService {
    private final MessageClient messageClient;

//    public MessageDto getMessage(final String message) {
//        return messageClient.getMessage(message);
//    }

    public UserMessagesDto getUserMessages(final long friendsID) {
        return messageClient.getUserMessages(friendsID);
    }
}
