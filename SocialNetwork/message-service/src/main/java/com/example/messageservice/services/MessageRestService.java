package com.example.messageservice.services;

import com.example.messageservice.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageRestService {
    private final List<String> messages = new ArrayList<>();

    public MessageDto getMessage(final String message) {
        messages.add(message);
        MessageDto messageDto = new MessageDto();
        messageDto.setText(message);
        return messageDto;
    }
}
