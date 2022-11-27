package org.example.springmvc.controllers.message;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.dto.FriendDto;
import org.example.springmvc.facades.MessageFacade;
import org.example.springmvc.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final MessageFacade messageFacade;
    private final AuthContext authContext;

    @GetMapping
    public String getUserMessages(final ModelMap model, final @RequestParam(name = "recipient_user") String recipientUsername) {
        model.addAllAttributes(messageFacade.getUserMessages(recipientUsername, authContext.getUser()));
        return "send_message";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String sendMessage(final FriendDto friendDto, final String messageText) {
        messageFacade.sendMessage(friendDto, messageText, authContext.getUser());
        return "redirect:message?recipient_user=" + friendDto.getFriendUsername();
    }
}
