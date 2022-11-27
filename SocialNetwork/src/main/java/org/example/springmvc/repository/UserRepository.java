package org.example.springmvc.repository;

import org.example.springmvc.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    long count();

    long countByUsernameStartingWith(String prefix);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(long id);

    Page<User> findUsersByUsernameStartingWith(String prefix, Pageable page);

    boolean existsUserByUsername(String username);

    boolean existsUserById(long id);

    @Transactional
    Integer deleteUserById(long id);

    @Query("select u from User u where u.id in (select f.firstUser.id from Friends f where f.secondUser.id =:userId) " +
            "or u.id in (select f.secondUser.id from Friends f where f.firstUser.id =:userId)")
    Optional<List<User>> findUserFriends(@Param("userId") long userId);
}
