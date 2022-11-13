package org.example.springmvc.controllers.friend;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.dto.FriendDto;
import org.example.springmvc.dto.RemoveFriendRequestDto;
import org.example.springmvc.facades.FriendFacade;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    private final FriendFacade friendFacade;
    private final UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, path = "/add")
    public RedirectView addFriend(final RemoveFriendRequestDto dto) {
        friendFacade.addFriend(dto.getFriendRequestId());
        RedirectView redirectView = new RedirectView("/friend_request/incoming");
        redirectView.setContextRelative(true);
        return redirectView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, path = "/remove")
    public String removeFriend(final HttpServletRequest req, final FriendDto dto) {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        User userFriend = userService.getUser(dto.getFriendUsername());
        friendFacade.removeFriend(userFriend, currentUser);
        return "redirect:/friends";
    }

    @GetMapping()
    public String getUserFriends(HttpServletRequest req, ModelMap model) {
        User currentUser = (User) req.getSession().getAttribute("currentUser");
        List<User> userFriendsUsernames = userService.getUserFriends(currentUser.getId());
        log.info("Getting friends for user: [{}]", currentUser.getUsername());
        model.addAttribute("userFriends", userFriendsUsernames);
        return "my_friends";
    }
}
