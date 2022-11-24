package org.example.springmvc.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.example.springmvc.model.User;
import org.example.springmvc.passwordhashing.PasswordHasher;
import org.example.springmvc.repository.UserDao;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Service
@Getter
@Slf4j
public class UserService {
    private final UserDao userDao;
    private final PasswordHasher passwordHasher;

    public boolean isExist(final String username) {
        return userDao.isExist(username);
    }

    public boolean verifyUser(final String username, final String password) throws InvalidCredentialException {
        log.info("Getting user from repository. User [{}]", username);
        User user = userDao.getUser(username).orElse(null);
        if (user != null) {
            if (!passwordHasher.verifyPassword(password, user.getPassword())) {
                throw new InvalidCredentialException();
            } else {
                return true;
            }
        } else {
            log.info("User provides invalid data. Username [{}]", username);
            throw new InvalidCredentialException();
        }
    }

    public void save(final String username, final String password,
                     final String role, final Date createdAt) throws IOException {
        String hashedPassword = passwordHasher.hashPassword(password);
        userDao.save(username, hashedPassword, role, createdAt);
        log.info("Saving user to the db. User [{}]", username);
    }

    public List<User> getAllFilteredUsers(final String prefix) {
        if (prefix != null) {
            return userDao.filterUsers(prefix);
        } else {
            return userDao.getAll();
        }
    }

    public User getUser(final String username) {
        log.info("Getting user from db by username. User [{}]", username);
        return userDao.getUser(username).orElseThrow();
    }

    public List<User> getUserFriends(final long userId) {
        return userDao.getUserFriends(userId);
    }
}
