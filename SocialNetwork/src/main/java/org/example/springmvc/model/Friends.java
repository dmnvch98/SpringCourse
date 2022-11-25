package org.example.springmvc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    public Friends(final User firstUser, final User secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }
}
