package com.example.messageservice.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "sender_user_id")
    private Long senderId;

    @Column(name = "recipient_user_id")
    private Long recipientId;

    @Column(name = "message_date")
    private Date messageDate;

    @Column(name = "message_text")
    private String messageText;

    @Column(name = "friends_id")
    private Long friendsId;
}
