package org.example.springmvc.controllers.friend;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.dto.FriendDto;
import org.example.springmvc.dto.RemoveFriendRequestDto;
import org.example.springmvc.facades.FriendFacade;
import org.example.springmvc.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/view/friends")
public class FriendController {
    private final FriendFacade friendFacade;
    private final AuthContext authContext;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView addFriend(final RemoveFriendRequestDto dto) {
        friendFacade.addFriend(dto.getFriendRequestId());
        RedirectView redirectView = new RedirectView("/view/friend_request/incoming");
        redirectView.setContextRelative(true);
        return redirectView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, path = "/remove")
    public String removeFriend(final FriendDto dto) {
        friendFacade.removeFriend(authContext.getUser(), dto.getFriendUsername());
        return "redirect:/view/friends";
    }

    @GetMapping()
    public String getUserFriends(final ModelMap model) {
        model.addAttribute("userFriends", friendFacade.getUserFriends(authContext.getUser()));
        return "my_friends";
    }
}
