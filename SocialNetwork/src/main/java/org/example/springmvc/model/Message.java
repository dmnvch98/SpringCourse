package org.example.springmvc.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
@NamedQueries({
        @NamedQuery(name = "getUserDialog", query = "select m from Message m where m.recipient = :user1 and "
                + " m.sender = :user2 or m.recipient = :user2 and m.sender = :user1 order by m.messageDate")
})
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
}
