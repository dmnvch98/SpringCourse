package org.example.springmvc.repository;

import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageJpa extends JpaRepository<Message, Long> {
    @Query("select m from Message m where m.recipient = :firstUser and "
            + " m.sender = :secondUser or m.recipient = :secondUser and m.sender = :firstUser order by m.messageDate")
    Optional<List<Message>> getMessages(@Param("firstUser") User firstUser, @Param("secondUser") User secondUser);
}
