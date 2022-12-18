package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.client.dto.SendMessageDto;
import org.example.springmvc.converter.MessageConverter;
import org.example.springmvc.dto.FriendDto;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.example.springmvc.service.FriendService;
import org.example.springmvc.service.MessageService;
import org.example.springmvc.service.UserService;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageFacade {
    private final MessageService messageService;
    private final UserService userService;

    private final MessageConverter messageConverter;

    private final FriendService friendsService;

    public void sendMessage(final FriendDto friendDto, final String messageText, final User sender) {
        User recipient = userService.getUser(friendDto.getFriendUsername());
        Friends friends = friendsService.getFriends(sender, recipient);
        Message message = new Message();
        message.setMessageText(messageText);
        message.setMessageDate(new Date());
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setFriends(friends);
        messageService.saveMessage(message);
        log.info("Message was sent. Sender=[{}], Recipient=[{}]", sender.getUsername(), recipient.getUsername());
    }

    public Message buildMessage(final SendMessageDto dto, final User currentUser) {
        Message message = messageConverter.dtoToMessage(dto);
        message.setSender(currentUser);
        message.setMessageDate(new Date());
        Friends friends = friendsService.getFriends(currentUser, message.getRecipient());
        message.setFriends(friends);
        return message;
    }

    public long getFriendsId(final String recipientUsername, final User currentUser) {
        User recipientUser = userService.getUser(recipientUsername);
        Friends friends = friendsService.getFriends(currentUser, recipientUser);
        return friends.getId();
    }

    public Map<String, Object> getUserMessages(String recipientUsername, User sender) {
        User recipient = userService.getUser(recipientUsername);
        Friends friends = friendsService.getFriends(sender, recipient);
        List<Message> userMessages = messageService.getUserMessagesByFriends(friends);
        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("userMessages", userMessages);
        attributesMap.put("recipientUser", recipient);
        return attributesMap;
    }
}
