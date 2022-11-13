package org.example.springmvc.service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.FriendDao;
import org.example.springmvc.repository.message.MessageDao;

import java.util.List;

@AllArgsConstructor
@Setter
public class FriendService {
    private FriendDao friendDao;
    private MessageDao messageDao;

    public void addFriend(final User firstUser, final User secondUser) {
        friendDao.addFriend(firstUser, secondUser);
    }

    public void removeFriend(final Friends friends) {
        friendDao.removeFriend(friends);
        List<Message> dialog = messageDao.getUserMessages(friends.getFirstUser(), friends.getSecondUser());
        messageDao.removeMessages(dialog);
    }

    public List<Friends> getFriendsRecords(final User firstUser, final User secondUser) {
        return friendDao.getFriends(firstUser, secondUser);
    }
}
