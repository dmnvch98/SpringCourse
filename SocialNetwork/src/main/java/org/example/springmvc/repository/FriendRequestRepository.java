package org.example.springmvc.repository;

import org.example.springmvc.model.FriendRequest;
import org.example.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    @Transactional
    void deleteFriendRequestById(long id);

    boolean existsFriendRequestByRequestUserAndApproveUser(User requestUser, User approveUser);

    Optional<List<FriendRequest>> findFriendRequestsByApproveUser(User approveUser);
    Optional<List<FriendRequest>> findFriendRequestsByRequestUser(User approveUser);

    Optional<FriendRequest> getFriendRequestById(long id);
}
