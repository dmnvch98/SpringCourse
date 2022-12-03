package org.example.springmvc.controllers.friendrequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.dto.RemoveFriendRequestDto;
import org.example.springmvc.facades.FriendRequestFacade;
import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.User;
import org.example.springmvc.service.FriendRequestService;
import org.example.springmvc.session.AuthContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/view/friend_request")
public class FriendRequestController {
    private final FriendRequestService friendRequestService;

    private final AuthContext authContext;

    private final FriendRequestFacade friendRequestFacade;

    @GetMapping(path = "/outgoing")
    public String getOutgoingFriendRequests(final ModelMap model) {
        User currentUser = authContext.getUser();
        List<FriendRequest> outgoingFriendRequests = friendRequestService.getOutgoingFriendRequests(currentUser);
        model.addAttribute("outgoingFriendRequests", outgoingFriendRequests);
        log.info("Getting outgoing friends requests for user: [{}] ", currentUser.getUsername());
        return "outgoing_friend_requests";
    }

    @GetMapping(path = "/incoming")
    public String getIncomingFriendRequests(final ModelMap model) {
        User currentUser = authContext.getUser();
        List<FriendRequest> incomingFriendRequests = friendRequestService.getIncomingFriendRequests(currentUser);
        model.addAttribute("incomingFriendRequests", incomingFriendRequests);
        log.info("Getting incoming friends requests for user: [{}] ", currentUser.getUsername());
        return "incoming_friend_requests";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView createFriendRequest(final @NotNull @NotEmpty String approveUsername) {
        friendRequestFacade.createFriendRequest(authContext.getUser(), approveUsername);
        RedirectView redirectView = new RedirectView("/view/allusers");
        redirectView.setContextRelative(true);
        return redirectView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, path = "/remove")
    public RedirectView removeFriendRequest(final RemoveFriendRequestDto dto) {
        friendRequestService.deleteRequest(dto.getFriendRequestId());
        RedirectView redirectView = new RedirectView("/view/allusers");
        redirectView.setContextRelative(true);
        log.info("Remove friends request. Id [{}]", dto.getFriendRequestId());
        return redirectView;
    }
}
