package org.example.springmvc.controllers.friendrequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.dto.RemoveFriendRequestDto;
import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.User;
import org.example.springmvc.service.FriendRequestService;
import org.example.springmvc.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/friend_request")
public class FriendRequestController {
    private final UserService userService;
    private final FriendRequestService friendRequestService;

    @GetMapping(path = "/outgoing")
    public String getOutgoingFriendRequests(final ModelMap model, final HttpServletRequest req) {
        String currentUsername = (String) req.getSession().getAttribute("username");
        List<FriendRequest> outgoingFriendRequests = friendRequestService.getOutgoingFriendRequests(currentUsername);
        model.addAttribute("outgoingFriendRequests", outgoingFriendRequests);
        log.info("Getting outgoing friends requests for user: [{}] ", currentUsername);
        return "outgoing_friend_requests";
    }

    @GetMapping(path = "/incoming")
    public String getIncomingFriendRequests(final ModelMap model, final HttpServletRequest req) {
        String currentUsername = (String) req.getSession().getAttribute("username");
        List<FriendRequest> incomingFriendRequests = friendRequestService.getIncomingFriendRequests(currentUsername);
        model.addAttribute("incomingFriendRequests", incomingFriendRequests);
        log.info("Getting incoming friends requests for user: [{}] ", currentUsername);
        return "incoming_friend_requests";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView createFriendRequest(final @NotNull @NotEmpty String approveUsername, final HttpServletRequest req) {
        User requestUser = (User) req.getSession().getAttribute("currentUser");
        User approveUser = userService.getUser(approveUsername);
        friendRequestService.createRequest(requestUser, approveUser);
        RedirectView redirectView = new RedirectView("/allusers");
        redirectView.setContextRelative(true);
        log.info("Create friend request. Initiator=[{}], Target=[{}]", requestUser, approveUser);
        return redirectView;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, path = "/remove")
    public RedirectView removeFriendRequest(final RemoveFriendRequestDto dto) {
        FriendRequest friendRequest = friendRequestService.getFriendRequest(dto.getFriendRequestId());
        friendRequestService.deleteRequest(friendRequest);
        RedirectView redirectView = new RedirectView("/allusers");
        redirectView.setContextRelative(true);
        log.info("Remove friends request. Initiator=[{}], Target=[{}]",
                friendRequest.getRequestUser(),
                friendRequest.getApproveUser());
        return redirectView;
    }
}
