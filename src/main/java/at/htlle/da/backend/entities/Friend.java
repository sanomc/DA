package at.htlle.da.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Friend {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "friend", nullable = false)
    private UserEntity friend;

    private Date friendsSince;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getFriend() {
        return friend;
    }

    public void setFriend(UserEntity friend) {
        this.friend = friend;
    }

    public Date getFriendsSince() {
        return friendsSince;
    }

    public void setFriendsSince(Date friendsSince) {
        this.friendsSince = friendsSince;
    }
}
