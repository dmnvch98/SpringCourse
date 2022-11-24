package org.example.springmvc.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.model.Friends;
import org.example.springmvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Slf4j
@RequiredArgsConstructor
@Repository
public class FriendRepository implements FriendDao {

    private final SessionFactory sessionFactory;

    @Override
    public void addFriend(final User firstUser, final User secondUser) {
        Friends friends = new Friends(firstUser, secondUser);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(friends);
            transaction.commit();
        } catch (Exception e) {
            log.error("An error occurred when trying to add friend. Users: [{}, {}]",
                    firstUser.getUsername(), secondUser.getUsername() + "\n" + e);
        }
    }

    @Override
    public void removeFriend(final Friends friends) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(friends);
            transaction.commit();
        } catch (Exception e) {
            log.error("An error occurred when trying to remove friend. Users: [{}, {}]",
                    friends.getFirstUser(), friends.getSecondUser() + "\n" + e);
        }
    }

    @Override
    public Friends getFriends(final User firstUser, final User secondUser) {
        Friends friends = new Friends();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("getFriendsRecord")
                    .setParameter("user1", firstUser)
                    .setParameter("user2", secondUser);
            friends = (Friends) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            log.error("An error occurred when trying getting friends. Users: [{}, {}]",
                    firstUser.getUsername(), secondUser.getUsername() + "\n" + e);
        }
        return friends;
    }
}
