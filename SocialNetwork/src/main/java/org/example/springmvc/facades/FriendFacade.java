package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.User;
import org.example.springmvc.service.FriendRequestService;
import org.example.springmvc.service.FriendService;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Component
public class FriendFacade {
    private final FriendService friendService;

    private final FriendRequestService friendRequestService;

    public void addFriend(final long friendRequestId) {
        FriendRequest friendRequest = friendRequestService.getFriendRequest(friendRequestId);

        log.info("New friends. User=[{}], User=[{}]",
                friendRequest.getRequestUser().getUsername(), friendRequest.getApproveUser().getUsername());

        User firstUser = friendRequest.getApproveUser();
        User secondUser = friendRequest.getRequestUser();
        friendService.addFriend(firstUser, secondUser);
        friendService.addFriend(secondUser, firstUser);
        friendRequestService.deleteRequest(friendRequest);
    }

    public void removeFriend(final User userFriend,final User currentUser) {
        List<Friends> friends = friendService.getFriends(currentUser, userFriend);
        friendService.removeFriend(friends.get(0));
        friendService.removeFriend(friends.get(1));
    }
}
