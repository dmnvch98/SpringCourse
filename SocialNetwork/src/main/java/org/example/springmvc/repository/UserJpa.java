package org.example.springmvc.repository;

import org.example.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserJpa extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    Optional<User> findUserByUsername(String username);

    Optional<List<User>> findUsersByUsernameIsLike(String prefix);

    Boolean existsUserByUsername(String username);

    @Query("select u from User u where u.id in (select f.firstUser.id from Friends f where f.secondUser.id =:userId) " +
            "or u.id in (select f.secondUser.id from Friends f where f.firstUser.id =:userId)")
    Optional<List<User>> findUserFriends(@Param("userId") long userId);
}
