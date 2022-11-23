package org.example.springmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.FriendDao;
import org.example.springmvc.repository.message.MessageDao;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendService {
    private final FriendDao friendDao;
    private final MessageDao messageDao;

    public void addFriend(final User firstUser, final User secondUser) {
        friendDao.addFriend(firstUser, secondUser);
    }

    public void removeFriend(final Friends friends) {
        friendDao.removeFriend(friends);
        List<Message> dialog = messageDao.getUserMessages(friends.getFirstUser(), friends.getSecondUser());
        messageDao.removeMessages(dialog);
    }

    public Friends getFriends(final User firstUser, final User secondUser) {
        return friendDao.getFriends(firstUser, secondUser);
    }
}
