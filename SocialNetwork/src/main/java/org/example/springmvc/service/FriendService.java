package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.FriendsRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FriendService {
    private final FriendsRepository friendsRepository;

    public void addFriend(final User firstUser, final User secondUser) {
        Friends friends = new Friends(firstUser, secondUser);
        friendsRepository.save(friends);
    }

    public void removeFriend(final Friends friends) {
        friendsRepository.delete(friends);
    }

    public Friends getFriends(final User firstUser, final User secondUser) {
        return friendsRepository.getFriends(firstUser, secondUser).orElse(null);
    }

    public Friends getFriends(final long id) {
        return friendsRepository.getFriendsById(id);
    }
}
