package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.User;
import org.example.springmvc.service.FriendRequestService;
import org.example.springmvc.service.FriendService;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FriendFacade {
    private final FriendService friendService;

    private final FriendRequestService friendRequestService;

    private final AuthContext authContext;

    private final UserService userService;

    public void addFriend(final long friendRequestId) {
        FriendRequest friendRequest = friendRequestService.getFriendRequest(friendRequestId);

        log.info("New friends. User=[{}], User=[{}]",
                friendRequest.getRequestUser().getUsername(), friendRequest.getApproveUser().getUsername());

        User firstUser = friendRequest.getApproveUser();
        User secondUser = friendRequest.getRequestUser();
        friendService.addFriend(firstUser, secondUser);
        friendRequestService.deleteRequest(friendRequest.getId());
    }

    public void removeFriend(final String friendUsername) {
        User currentUser = authContext.getUser();
        User friendUser = userService.getUser(friendUsername);
        Friends friends = friendService.getFriends(currentUser, friendUser);
        log.info("User removed friend. Initiator: [{}]", currentUser.getUsername());
        friendService.removeFriend(friends);
    }

    public List<User> getUserFriends() {
        User currentUser = authContext.getUser();
        log.info("Getting friends for user: [{}]", currentUser.getUsername());
        return userService.getUserFriends(currentUser.getId());
    }
}
