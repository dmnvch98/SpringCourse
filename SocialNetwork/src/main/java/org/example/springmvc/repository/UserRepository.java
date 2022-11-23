package org.example.springmvc.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Log4j2
@RequiredArgsConstructor
@Repository
public class UserRepository implements UserDao {
    private final SessionFactory sessionFactory;

    @Override
    public void save(final String username, final String password, final String role, final Date createdAt) {
        User user = new User(username, password, role, createdAt);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            log.error("An error occurred when trying to save user. User - [{}]", username + "\n" + e);
        }
    }

    @Override
    public boolean isExist(final String username) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("isExists")
                    .setParameter("username", username);
            transaction.commit();
            return query.getSingleResult() != null;
        } catch (Exception e) {
            if (!(e instanceof NoResultException)) {
                log.error("An error occurred when checking if user exists. User [{}]", username + "\n" + e);
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        List<User> listOfUser = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            listOfUser = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            log.error("An error occurred when getting all users" + "\n" + e);
        }
        return listOfUser;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> filterUsers(final String prefix) {
        List<User> filteredUsers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("filterUsers").setParameter("prefix", prefix);
            filteredUsers = (List<User>) query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            log.error("An error occurred when trying to filter users with prefix [{}]", prefix + "\n" + e);
        }
        return filteredUsers;
    }

    @Override
    public Optional<User> getUser(final String username) {
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.getNamedQuery("getUser")
                    .setParameter("username", username);
            user = (User) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if (!(e instanceof NoResultException)) {
                log.error("An error occurred when trying to get user info. UserId [{}]", username + "\n" + e);
            }
        }
        return user != null ? Optional.of(user) : Optional.empty();
        //Optional.ofNullable(user) Идея подсказывает заменить на Optional.empty(), т.к. user = null
    }

    @SuppressWarnings("unchecked")
    public List<User> getUserFriends(final long userId) {
        List<User> userFriends = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            userFriends = session.getNamedQuery("getFirstPartUserFriends")
                    .setParameter("userId", userId).getResultList();
            userFriends.addAll(
                    session.getNamedQuery("getSecondPartUserFriends")
                            .setParameter("userId", userId).getResultList()
            );
            transaction.commit();
        } catch (Exception e) {
            log.error("An error occurred when trying to get user friends. UserId [{}]", userId + "\n" + e);
        }
        return userFriends;
    }

}
