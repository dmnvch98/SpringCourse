package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.example.springmvc.service.MessageService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j2
@RequiredArgsConstructor
@Component
public class MessageFacade {
    private final MessageService messageService;

    public void sendMessage(final User sender, final User recipient, final String messageText) {
        Message message = new Message();
        message.setMessageText(messageText);
        message.setMessageDate(new Date());
        message.setSender(sender);
        message.setRecipient(recipient);
        messageService.saveMessage(message);
        log.info("Message was sent. Sender=[{}], Recipient=[{}]", sender.getUsername(), recipient.getUsername());
    }
}
