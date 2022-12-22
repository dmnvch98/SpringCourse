package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendRequestService {
    private final FriendRequestRepository friendRequestRepository;

    public void createRequest(final User requestUser, final User approveUser) {
        if (!(friendRequestRepository
                .existsFriendRequestByRequestUserAndApproveUser(requestUser, approveUser))) {
            FriendRequest friendRequest = new FriendRequest(requestUser, approveUser);
            friendRequestRepository.save(friendRequest);
        }
    }

    public void deleteRequest(final long id) {
        friendRequestRepository.deleteFriendRequestById(id);
    }

    public List<FriendRequest> getIncomingFriendRequests(final User approveUser) {
        return friendRequestRepository.findFriendRequestsByApproveUser(approveUser).orElse(null);
    }

    public List<FriendRequest> getOutgoingFriendRequests(final User requestUser) {
        return friendRequestRepository.findFriendRequestsByRequestUser(requestUser).orElse(null);
    }

    public FriendRequest getFriendRequest(final long id) {
        return friendRequestRepository.getFriendRequestById(id).orElse(null);
    }
}
