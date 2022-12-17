package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.client.MessageClient;
import org.example.springmvc.client.dto.MessageDto;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageRestService {
    private final MessageClient messageClient;

    public MessageDto getMessage(final String message) {
        return messageClient.getMessage(message);
    }
}
