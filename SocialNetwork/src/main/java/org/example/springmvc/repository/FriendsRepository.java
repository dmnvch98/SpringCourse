package org.example.springmvc.repository;

import org.example.springmvc.model.Friends;
import org.example.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
    @Query("select f from Friends f where f.firstUser = : firstUser "
            + "and f.secondUser = :secondUser or f.firstUser = :secondUser and f.secondUser = :firstUser")
    Optional<Friends> getFriends(@Param("firstUser") User firstUser, @Param("secondUser") User secondUser);

    Friends getFriendsById(long id);
}
