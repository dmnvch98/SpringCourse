package org.example.springmvc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "friendRequest")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "getIncomingFriendRequests", query = "select ifr from FriendRequest ifr "
                + "where ifr.approveUser.username = :username"),
        @NamedQuery(name = "getOutgoingFriendRequest", query = "select ofr from FriendRequest ofr "
                + "where ofr.requestUser.username = :username"),
        @NamedQuery(name = "getFriendRequestById", query = "select fr from FriendRequest fr "
                + "where fr.id = :requestId"),
        @NamedQuery(name = "isFriendRequestExists", query = "select fr from FriendRequest fr"
                + " where fr.approveUser = : approveUser and fr.requestUser = :requestUser")
})
public class FriendRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @ManyToOne
    @JoinColumn(name = "requester_user_id")
    private User requestUser;
    @ManyToOne
    @JoinColumn(name = "approver_user_id")
    private User approveUser;

    public FriendRequest(final User requestUser, final User approveUser) {
        this.requestUser = requestUser;
        this.approveUser = approveUser;
    }
}
