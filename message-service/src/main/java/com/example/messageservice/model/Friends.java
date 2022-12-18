package com.example.messageservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "friends")
@Data
@NoArgsConstructor
public class Friends {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @ManyToOne
    @JoinColumn(name = "first_user")
    private User firstUser;
    @ManyToOne
    @JoinColumn(name = "second_user")
    private User secondUser;

    @OneToMany(mappedBy = "friends", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> friendsMessages;

    public Friends(final User firstUser, final User secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }
}
