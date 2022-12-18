package org.example.springmvc.controllers.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.client.dto.SendMessageDto;
import org.example.springmvc.client.dto.UserMessagesDto;
import org.example.springmvc.facades.MessageFacade;
import org.example.springmvc.model.User;
import org.example.springmvc.service.FriendService;
import org.example.springmvc.service.MessageRestService;
import org.example.springmvc.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageRestController {
    private final MessageRestService messageRestService;
    private final UserService userService;
    private final FriendService friendService;

    private final MessageFacade messageFacade;

    @GetMapping("/{recipientUsername}")
    public ResponseEntity<UserMessagesDto> getUserMessages(final @PathVariable(name = "recipientUsername")
                                                           String recipientUsername) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUser(currentPrincipalName);
        return ResponseEntity.ok(messageRestService.
                getUserMessages(
                        messageFacade.
                                getFriendsId(recipientUsername, user)
                )
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserMessagesDto> sendMessage(@RequestBody final SendMessageDto sendMessageDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUser(currentPrincipalName);
        return ResponseEntity.ok(messageRestService.
                sendMessage
                        (messageFacade.
                                buildMessage(sendMessageDto, user)
                        )
        );
    }
}