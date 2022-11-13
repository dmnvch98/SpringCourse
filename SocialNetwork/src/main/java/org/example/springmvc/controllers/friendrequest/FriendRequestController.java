package org.example.springmvc.controllers.friendrequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.dto.CreateFriendRequestDto;
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
import java.util.List;

@Controller
@RequestMapping("/friendrequest")
@RequiredArgsConstructor
@Log4j2
public class FriendRequestController {
    private final UserService userService;
    private final FriendRequestService friendRequestService;

    @GetMapping(path = "outgoing")
    public String getOutgoingFriendRequests(ModelMap model, HttpServletRequest req) {
        String currentUsername = (String) req.getSession().getAttribute("username");
        List<FriendRequest> outgoingFriendRequests = friendRequestService.getOutgoingFriendRequests(currentUsername);
        model.addAttribute("outgoingFriendRequests", outgoingFriendRequests);
        /*
        * Если в маппинге указан path=outgoing, то редиректит сюда /friendrequest/view/outgoing_friend_requests.jsp
        * Если маппинг не указывать (ну и соответсвенно убрать outgoing из пути в jsp), то редиректит нормально
        */
        // return "outgoing_friend_requests";


        // return new RedirectView( "outgoing_friend_requests") friendrequest/outgoing_friend_requests
        return "outgoing_friend_requests";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, path = "/create")
    public RedirectView createFriendRequest(final CreateFriendRequestDto friendRequestDto, HttpServletRequest req) {
        User requestUser = userService.getUser(friendRequestDto.getRequestUsername());
        User approveUser = userService.getUser(friendRequestDto.getApproveUsername());

        log.info("Create friend request. Initiator=[{}], Target=[{}]", requestUser, approveUser);
        friendRequestService.createRequest(requestUser, approveUser);
        return new RedirectView(req.getContextPath() + "/allusers");
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, path = "/remove")
    public RedirectView removeFriendRequest(final RemoveFriendRequestDto friendRequestDto, HttpServletRequest req) {
        FriendRequest friendRequest = friendRequestService.getFriendRequest(friendRequestDto.getId());

        log.info("Remove friends request. Initiator=[{}], Target=[{}]",
                friendRequest.getRequestUser(), friendRequest.getApproveUser());
        friendRequestService.deleteRequest(friendRequest);

        return new RedirectView(req.getContextPath() + "/allusers");
    }


}
