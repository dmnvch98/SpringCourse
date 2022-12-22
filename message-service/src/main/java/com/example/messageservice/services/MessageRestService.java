package com.example.messageservice.services;

import com.example.messageservice.model.Message;
import com.example.messageservice.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageRestService {
    private final MessageRepository messageRepository;

    public List<Message> getUserMessages(final long friendsId) {
        return messageRepository.
                findMessagesByFriendsIdOrderById(friendsId)
                .orElse(null);
    }

    public void saveMessage(final Message message) {
        messageRepository.save(message);
    }
}
