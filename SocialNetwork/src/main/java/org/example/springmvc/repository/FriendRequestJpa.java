package org.example.springmvc.repository;

import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FriendRequestJpa extends JpaRepository<FriendRequest, Long> {
    @Transactional
    void deleteFriendRequestById(long id);

    boolean existsFriendRequestByRequestUserAndApproveUser(User requestUser, User approveUser);

    void getFriendRequestByApproveUserAndRequestUser(long approveUserId, long requestUserId);
}
