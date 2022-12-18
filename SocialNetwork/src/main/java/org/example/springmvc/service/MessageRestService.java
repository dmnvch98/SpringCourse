package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.client.MessageClient;
import org.example.springmvc.client.dto.UserMessagesDto;
import org.example.springmvc.model.Message;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageRestService {
    private final MessageClient messageClient;

    public UserMessagesDto getUserMessages(final long friendsID) {
        return messageClient.getUserMessages(friendsID);
    }

    public UserMessagesDto sendMessage(final Message message) {
        return messageClient.sendMessage(message);
    }
}
