package com.example.messageservice.services;

import com.example.messageservice.converter.MessageConverter;
import com.example.messageservice.dto.MessageDto;
import com.example.messageservice.dto.UserMessagesDto;
import com.example.messageservice.model.Message;
import com.example.messageservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageRestService {
    private final List<String> messages = new ArrayList<>();
    private final MessageRepository messageRepository;
    private final MessageConverter messageConverter;

    public MessageDto getMessage(final String message) {
        messages.add(message);
        MessageDto messageDto = new MessageDto();
        messageDto.setText(message);
        return messageDto;
    }

    public UserMessagesDto getUserMessages(final long friendsId) {
        List<Message> userMessages = messageRepository.findMessagesByFriends_Id(friendsId).orElse(null);
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
}
