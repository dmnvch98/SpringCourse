package org.example.springmvc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "getUserDialog", query = "select m from Message m where m.recipient = :user1 and "
                + " m.sender = :user2 or m.recipient = :user2 and m.sender = :user1 order by m.messageDate")
        //select * from messages where recipient_user_id = 3 and sender_user_id = 5 or recipient_user_id = 5 and sender_user_id = 3;
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

    public Message(final User sender, final User recipient, final Date messageDate, final String messageText) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageDate = messageDate;
        this.messageText = messageText;
    }
}
