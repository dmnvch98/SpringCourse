package com.example.messageservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageRestService {
    private final List<String> messages = new ArrayList<>();

    public String getMessage(final String  message) {
        messages.add(message);
        return message;
    }
}
