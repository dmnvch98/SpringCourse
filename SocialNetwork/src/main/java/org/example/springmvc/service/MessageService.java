package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.Message;
import org.example.springmvc.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;

    public void saveMessage(final Message message) {
        messageRepository.save(message);
    }

    public List<Message> getUserMessagesByFriends(Friends friends) {
        return messageRepository.findMessagesByFriends(friends).orElse(null);
    }
}
