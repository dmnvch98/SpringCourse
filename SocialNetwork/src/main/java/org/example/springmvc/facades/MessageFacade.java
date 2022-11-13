package org.example.springmvc.facades;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;

import org.example.springmvc.service.MessageService;
import org.example.springmvc.service.UserService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Component
public class MessageFacade {
    private final MessageService messageService;

    public void sendMessage(User sender, User recipient, Date messageDate, String messageText) {
        Message message = new Message();
        message.setMessageText(messageText);
        message.setMessageDate(messageDate);
        message.setSender(sender);
        message.setRecipient(recipient);
        messageService.saveMessage(message);
        log.info("Send message. Sender=[{}], Recipient=[{}]", sender.getUsername(), recipient.getUsername());
    }
}
