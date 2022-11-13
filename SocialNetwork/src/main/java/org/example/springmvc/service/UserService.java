package org.example.springmvc.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.User;
import org.example.springmvc.passwordhashing.PasswordHasher;
import org.example.springmvc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Service
@Getter
public class UserService {
    private final UserDao userDao;
    @Autowired
    private final PasswordHasher passwordHasher;

    public boolean isExist(final String username) {
        return userDao.isExist(username);
    }
    public boolean verifyUser(final String username, final String password) {
        User user = userDao.getUserIfExists(username).orElse(null);
        if (user != null) {
            return passwordHasher.verifyPassword(password, user.getPassword());
        }
        return false;
    }

    public void save(final String username, final String password,
                     final String role, final Date createdAt) throws IOException {
        if (isExist(username)) {
            throw new IOException("User already exists");
        } else {
            userDao.save(username, password, role, createdAt);
        }
    }

    public List<User> getAllFilteredUsers(final String prefix) {
        if (prefix != null) {
            return userDao.filterUsers(prefix);
        } else {
            return userDao.getAll();
        }
    }

    public User getUser(final String username) {
        return userDao.getUserIfExists(username).orElse(new User());
    }

    public List<User> getUserFriends(final long userId) {
        return userDao.getUserFriends(userId);
    }
}
