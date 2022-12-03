package org.example.springmvc.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.config.security.PasswordConfig;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.example.springmvc.model.User;
import org.example.springmvc.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Getter
@Slf4j
public class UserService {
    private final UserRepository userJpaDao;

    private final PasswordConfig passwordConfig;

    public boolean isExist(final String username) {
        log.info("Checking if user exists. Username [{}]", username);
        return userJpaDao.existsUserByUsername(username);
    }

    public boolean isExist(final long id) {
        log.info("Checking if user exists. Id [{}]", id);
        return userJpaDao.existsUserById(id);
    }

    public boolean verifyUser(final String username, final String password) throws InvalidCredentialException {
        log.info("Getting user from repository. User [{}]", username);
        User user = userJpaDao.findUserByUsername(username).orElse(null);
        if (user != null) {
            return passwordConfig.passwordEncoder().matches(password, user.getPassword());
        } else {
            return false;
        }
    }

    public void save(final String username, final String password, String role,
                     final Date createdAt) throws IOException {
        String hashedPassword = passwordConfig.passwordEncoder().encode(password);
        User user = new User(username, hashedPassword, role, createdAt);
        userJpaDao.save(user);
        log.info("Saving user to the db. User [{}]", username);
    }

    public List<User> getFilteredUsers(final String prefix, final int pageNumber, final int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        if (prefix != null) {
            log.info("Getting filtered users");
            return userJpaDao.findUsersByUsernameStartingWith(prefix, page).getContent();
        } else {
            log.info("Getting all users");
            return userJpaDao.findAll(page).getContent();
        }
    }

    public User getUser(final String username) {
        log.info("Getting user from db by username. User [{}]", username);
        return userJpaDao.findUserByUsername(username).orElse(null);
    }

    public User getUser(final long id) {
        log.info("Getting user from db by id. UserId [{}]", id);
        return userJpaDao.findUserById(id).orElse(null);
    }

    public List<User> getUserFriends(final long userId) {
        log.info("Getting user friends. User id [{}]", userId);
        return userJpaDao.findUserFriends(userId).orElse(null);
    }

    public long countAllUsers() {
        log.info("Getting all users quantity");
        return userJpaDao.count();
    }

    public long countFilteredUsers(final String prefix) {
        log.info("Counting filtered users with prefix [{}]", prefix);
        return userJpaDao.countByUsernameStartingWith(prefix);
    }

    public Integer deleteUserById(final long id) {
        return userJpaDao.deleteUserById(id);
    }

    public void updateUser(User user) {
        userJpaDao.save(user);
    }
}
