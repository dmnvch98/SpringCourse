package org.example.springmvc.controllers.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.dto.FriendDto;
import org.example.springmvc.facades.MessageFacade;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.example.springmvc.service.MessageService;
import org.example.springmvc.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    private final MessageFacade messageFacade;

    @GetMapping
    public String getUserMessages(final HttpServletRequest req, final Model model,
                                  final @RequestParam(name = "recipient_user") String recipientUsername) {
        User sender = (User) req.getSession().getAttribute("currentUser");
        User recipient = userService.getUser(recipientUsername);
        List<Message> userMessages = messageService.getUserMessages(sender, recipient);
        model.addAttribute("userMessages", userMessages);
        model.addAttribute("recipientUser", recipient);
        return "send_message";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String sendMessage(final HttpServletRequest req, final FriendDto friendDto, final MessageTextDto messageTextDto) {
        User sender = (User) req.getSession().getAttribute("currentUser");
        User recipient = userService.getUser(friendDto.getFriendUsername());
        messageFacade.sendMessage(sender, recipient, messageTextDto.getMessageText());
        return "redirect:message?recipient_user=" + recipient.getUsername();
    }
}
