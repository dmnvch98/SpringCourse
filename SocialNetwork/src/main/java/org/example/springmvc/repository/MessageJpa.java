package org.example.springmvc.repository;

import org.example.springmvc.model.Friends;
import org.example.springmvc.model.Message;
import org.example.springmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageJpa extends JpaRepository<Message, Long> {
    Optional<List<Message>> findMessagesByFriends(Friends friends);
}
