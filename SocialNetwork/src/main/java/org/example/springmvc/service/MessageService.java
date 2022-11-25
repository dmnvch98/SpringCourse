package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.Message;
import org.example.springmvc.repository.MessageJpa;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageJpa messageJpa;

    public void saveMessage(final Message message) {
        messageJpa.save(message);
    }

    public List<Message> getUserMessagesByFriends(Friends friends) {
        return messageJpa.findMessagesByFriends(friends).orElse(null);
    }
}
