package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.dto.FriendDto;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.example.springmvc.service.MessageService;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.springframework.stereotype.Component;

import java.util.*;

@Log4j2
@RequiredArgsConstructor
@Component
public class MessageFacade {
    private final MessageService messageService;
    private final UserService userService;

    private final AuthContext authContext;

    public void sendMessage(final FriendDto friendDto, final String messageText) {
        User sender = authContext.getUser();
        User recipient = userService.getUser(friendDto.getFriendUsername());
        Message message = new Message();
        message.setMessageText(messageText);
        message.setMessageDate(new Date());
        message.setSender(sender);
        message.setRecipient(recipient);
        messageService.saveMessage(message);
        log.info("Message was sent. Sender=[{}], Recipient=[{}]", sender.getUsername(), recipient.getUsername());
    }

    public Map<String, Object> getUserMessages(String recipientUsername) {
        User sender = authContext.getUser();
        User recipient = userService.getUser(recipientUsername);
        List<Message> userMessages = messageService.getUserMessages(sender, recipient);
        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("userMessages", userMessages);
        attributesMap.put("recipientUser", recipient);
        return attributesMap;
    }
}
