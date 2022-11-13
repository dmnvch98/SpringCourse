package org.example.springmvc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "friends")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "getFriendsRecord", query = "select f from Friends f where f.firstUser = : user1 "
                + "and f.secondUser = :user2 or f.firstUser = :user2 and f.secondUser = :user1")
})
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

    public Friends(final User firstUser, final User secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }
}
