package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.MessageJpa;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageJpa messageJpa;

    public void saveMessage(final Message message) {
        messageJpa.save(message);
        //messageDao.saveMessage(message);
    }

    public List<Message> getUserMessages(final User firstUser, final User secondUser) {
        //return messageDao.getUserMessages(user1, user2);
        return messageJpa.getMessages(firstUser, secondUser).orElse(null);
    }

    public void removeMessages(List<Message> messages) {
        //messageDao.removeMessages(messages);
        messageJpa.deleteAll(messages);
    }
}
