package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.model.User;
import org.example.springmvc.service.FriendRequestService;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FriendRequestFacade {
    private final AuthContext authContext;
    private final UserService userService;
    private final FriendRequestService friendRequestService;

    public void createFriendRequest(String approveUsername) {
        User requestUser = authContext.getUser();
        User approveUser = userService.getUser(approveUsername);
        friendRequestService.createRequest(requestUser, approveUser);
        log.info("Create friend request. Initiator=[{}], Target=[{}]", requestUser, approveUser);
    }

}
