package com.example.messageservice.repository;

import com.example.messageservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<List<Message>> findMessagesByFriends_IdOrderById(long friendsId);

}
