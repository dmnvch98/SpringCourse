package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.FriendRequestDao;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendRequestService {
    private final FriendRequestDao friendRequestDao;

    public void createRequest(final User requestUser, final User approveUser) {
        if (!(friendRequestDao.isExists(requestUser, approveUser))) {
            friendRequestDao.createRequest(requestUser, approveUser);
        }
    }

    public void deleteRequest(final FriendRequest friendRequest) {
        friendRequestDao.deleteRequest(friendRequest);
    }

    public List<FriendRequest> getIncomingFriendRequests(final String username) {
        return friendRequestDao.getIncomingFriendRequests(username);
    }

    public List<FriendRequest> getOutgoingFriendRequests(final String username) {
        return friendRequestDao.getOutgoingFriendRequests(username);
    }

    public FriendRequest getFriendRequest(final long id) {
        return friendRequestDao.getFriendRequest(id);
    }
}
