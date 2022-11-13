package org.example.springmvc.repository;

import org.example.springmvc.model.Friends;
import org.example.springmvc.model.User;

import java.util.List;

public interface FriendDao {
    void addFriend(User firstUser, User secondUser);

    void removeFriend(Friends friends);

    List<Friends> getFriends(User firstUser, User secondUser);
}
