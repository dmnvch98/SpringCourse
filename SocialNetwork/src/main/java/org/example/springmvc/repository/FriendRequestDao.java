package org.example.springmvc.repository;

import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.User;

import java.util.List;

public interface FriendRequestDao {
    void createRequest(User requestUser, User approveUser);
    void deleteRequest(FriendRequest friendRequest);
    List<FriendRequest> getIncomingFriendRequests(String username);
    List<FriendRequest> getOutgoingFriendRequests(String username);
    FriendRequest getFriendRequest(long id);
    boolean isExists(User requestUser, User approveUser);
}
