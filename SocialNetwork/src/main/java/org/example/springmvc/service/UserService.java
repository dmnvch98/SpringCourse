package org.example.springmvc.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.example.springmvc.model.User;
import org.example.springmvc.passwordhashing.PasswordHasher;
import org.example.springmvc.repository.UserJpa;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Service
@Getter
@Slf4j
public class UserService {
    private final UserJpa userJpaDao;
    private final PasswordHasher passwordHasher;

    public boolean isExist(final String username) {
        return userJpaDao.existsUserByUsername(username);
    }

    public boolean verifyUser(final String username, final String password) throws InvalidCredentialException {
        log.info("Getting user from repository. User [{}]", username);
        User user = userJpaDao.findUserByUsername(username).orElse(null);
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
        User user = new User(username, hashedPassword, role, createdAt);
        userJpaDao.save(user);
        log.info("Saving user to the db. User [{}]", username);
    }

    public List<User> getAllFilteredUsers(final String prefix) {
        if (prefix != null) {
            return userJpaDao.findUsersByUsernameIsLike(prefix).orElseThrow();
        } else {
            return userJpaDao.findAll();
        }
    }

    public User getUser(final String username) {
        log.info("Getting user from db by username. User [{}]", username);
        return userJpaDao.findUserByUsername(username).orElseThrow();
    }

    public List<User> getUserFriends(final long userId) {
        return userJpaDao.findUserFriends(userId).orElse(null);
    }
}
