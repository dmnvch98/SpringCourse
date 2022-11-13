package org.example.springmvc.facades;

import lombok.extern.log4j.Log4j2;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;

import org.example.springmvc.service.MessageService;
import org.example.springmvc.service.UserService;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

@Log4j2
public class MessageFacade {
    private final UserService userService;
    private final MessageService messageService;

    private User sender;
    private User recipient;

    public MessageFacade(final UserService userService, final MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    public void sendMessage(final HttpServletRequest req) {
        Date messageDate = new Date();
        String messageText = req.getParameter("message_text");
        Message message = new Message(sender, recipient, messageDate, messageText);
        messageService.saveMessage(message);

        log.info("Send message. Sender=[{}], Recipient=[{}]", sender.getUsername(), recipient.getUsername());
    }

    public void getMessages(final HttpServletRequest req) {
        String recipientUsername = req.getParameter("recipient_user");
        req.getSession().setAttribute("recipient_message_user", recipientUsername);
        sender = userService.getUser((String) req.getSession().getAttribute("username"));
        recipient = userService.getUser(recipientUsername);
        List<Message> userMessages = messageService.getUserMessages(sender, recipient);
        req.setAttribute("userDialog", userMessages);
    }
}
