package org.example.springmvc.repository;

import org.example.springmvc.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    void save(String username, String password, String role, Date createdAt);
    List<User> getAll();
    List<User> filterUsers(String prefix);
    boolean isExist(String username);
    Optional<User> getUserIfExists(String username);
    List<User> getUserFriends(long userId);
}
