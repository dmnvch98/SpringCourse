package org.example.springmvc.controllers.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.client.dto.MessageDto;
import org.example.springmvc.client.dto.UserMessagesDto;
import org.example.springmvc.config.security.service.AuthService;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.User;
import org.example.springmvc.service.FriendService;
import org.example.springmvc.service.MessageRestService;
import org.example.springmvc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageRestController {
    private final MessageRestService messageRestService;
    private final UserService userService;
    private final FriendService friendService;


//    @GetMapping("/{message}")
//    public ResponseEntity<MessageDto> getMessage(@PathVariable final String message) {
//        return ResponseEntity.ok(messageRestService.getMessage(message));
//    }
    @GetMapping("/{recipientUsername}")
    public ResponseEntity<UserMessagesDto> getUserMessages(final @PathVariable(name = "recipientUsername")
                                                               String recipientUsername) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username;
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails)principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUser(currentPrincipalName);
        User recipientUser = userService.getUser(recipientUsername);
        Friends friends = friendService.getFriends(user, recipientUser);
        return ResponseEntity.ok(messageRestService.getUserMessages(friends.getId()));
    }
}