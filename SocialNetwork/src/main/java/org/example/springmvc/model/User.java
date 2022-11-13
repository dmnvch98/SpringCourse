package org.example.springmvc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "filterUsers", query = "select u from User u where u.username like CONCAT(:prefix,'%')"),
        @NamedQuery(name = "isExists", query = "select u from User u where u.username = :username "),
        @NamedQuery(name = "getUser", query = "select u from User u where u.username = :username"),
        @NamedQuery(name = "getUserFriends", query = "select u from User u "
                + "where u.id in (select f.secondUser.id from Friends f where f.firstUser.id = :userId)")
})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;
    @Column(name = "created_at")
    private Date createdAt;

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public User(final String username, final String password, final String role, final Date createdAt) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }
}
