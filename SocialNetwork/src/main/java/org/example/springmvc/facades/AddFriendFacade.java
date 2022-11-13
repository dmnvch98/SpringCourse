package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.User;
import org.example.springmvc.service.FriendRequestService;
import org.example.springmvc.service.FriendService;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RequiredArgsConstructor
public class AddFriendFacade {
    private final FriendService friendService;

    private final FriendRequestService friendRequestService;

    public void addFriend(final HttpServletRequest req) {
        int friendRequestId = Integer.parseInt(req.getParameter("friendrequestid"));
        FriendRequest friendRequest = friendRequestService.getFriendRequest(friendRequestId);

        log.info("New friends. User=[{}], User=[{}]",
                friendRequest.getRequestUser(), friendRequest.getApproveUser());

        User firstUser = friendRequest.getApproveUser();
        User secondUser = friendRequest.getRequestUser();
        friendService.addFriend(firstUser, secondUser);
        friendService.addFriend(secondUser, firstUser);
        friendRequestService.deleteRequest(friendRequest);
    }
}
