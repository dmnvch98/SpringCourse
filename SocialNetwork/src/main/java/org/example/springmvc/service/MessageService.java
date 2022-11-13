package org.example.springmvc.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.message.MessageDao;

import java.util.List;

@AllArgsConstructor
@Setter
public class MessageService {
    private MessageDao messageDao;

    public void saveMessage(final Message message) {
        messageDao.saveMessage(message);
    }

    public List<Message> getUserMessages(final User user1, final User user2) {
        return messageDao.getUserMessages(user1, user2);
    }
}
