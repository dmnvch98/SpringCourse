package org.example.springmvc.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.config.security.PasswordConfig;
import org.example.springmvc.converter.RecipientIdConverter;
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
public class UserService implements RecipientIdConverter {
    private final UserRepository userRepository;

    private final PasswordConfig passwordConfig;

    public boolean isExist(final String username) {
        log.info("Checking if user exists. Username [{}]", username);
        return userRepository.existsUserByUsername(username);
    }

    public boolean isExist(final long id) {
        log.info("Checking if user exists. Id [{}]", id);
        return userRepository.existsUserById(id);
    }

    public boolean verifyUser(final String username, final String password) throws InvalidCredentialException {
        log.info("Getting user from repository. User [{}]", username);
        User user = userRepository.findUserByUsername(username).orElse(null);
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
        userRepository.save(user);
        log.info("Saving user to the db. User [{}]", username);
    }

    public List<User> getFilteredUsers(final String prefix, final int pageNumber, final int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        if (prefix != null) {
            log.info("Getting filtered users");
            return userRepository.findUsersByUsernameStartingWith(prefix, page).getContent();
        } else {
            log.info("Getting all users");
            return userRepository.findAll(page).getContent();
        }
    }

    public User getUser(final String username) {
        log.info("Getting user from db by username. User [{}]", username);
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public User getUser(final long id) {
        log.info("Getting user from db by id. UserId [{}]", id);
        return userRepository.findUserById(id).orElse(null);
    }

    public List<User> getUserFriends(final long userId) {
        log.info("Getting user friends. User id [{}]", userId);
        return userRepository.findUserFriends(userId).orElse(null);
    }

    public long countAllUsers() {
        log.info("Getting all users quantity");
        return userRepository.count();
    }

    public long countFilteredUsers(final String prefix) {
        log.info("Counting filtered users with prefix [{}]", prefix);
        return userRepository.countByUsernameStartingWith(prefix);
    }

    public Integer deleteUserById(final long id) {
        return userRepository.deleteUserById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User recipientIdToUser(long recipientId) {
        return userRepository.findUserById(recipientId).orElse(null);
    }
}
