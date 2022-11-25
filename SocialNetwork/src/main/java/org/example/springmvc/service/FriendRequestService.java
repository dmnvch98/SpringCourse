package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.FriendRequestDao;
import org.example.springmvc.repository.FriendRequestJpa;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendRequestService {
    private final FriendRequestDao friendRequestDao;

    private final FriendRequestJpa friendRequestJpa;

    public void createRequest(final User requestUser, final User approveUser) {
        if (!(friendRequestJpa
                .existsFriendRequestByRequestUserAndApproveUser(requestUser, approveUser))) {
            FriendRequest friendRequest = new FriendRequest(requestUser, approveUser);
            friendRequestJpa.save(friendRequest);
        }
    }

    public void deleteRequest(final long id) {
        friendRequestJpa.deleteFriendRequestById(id);
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
