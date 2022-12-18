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
    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "recipient_user_id")
    private User recipient;
    @Column(name = "message_date")
    private Date messageDate;
    @Column(name = "message_text")
    private String messageText;
    @ManyToOne
    private Friends friends;
}
