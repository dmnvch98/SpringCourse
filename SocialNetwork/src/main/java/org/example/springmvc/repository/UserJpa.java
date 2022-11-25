package org.example.springmvc.repository;

import org.example.springmvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserJpa extends JpaRepository<User, Long> {
    @Query("select count(u.id) from User u")
    Optional<Integer> countUsers();

    @Query("select count (u.id) from User u where u.username like :username%")
    int countFilteredUsers(@Param("username") String username);

    Optional<User> findUserByUsername(String username);

    Page<User> findUsersByUsernameStartingWith(String prefix, Pageable page);

    Boolean existsUserByUsername(String username);

    @Query("select u from User u where u.id in (select f.firstUser.id from Friends f where f.secondUser.id =:userId) " +
            "or u.id in (select f.secondUser.id from Friends f where f.firstUser.id =:userId)")
    Optional<List<User>> findUserFriends(@Param("userId") long userId);
}
