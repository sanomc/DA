package at.htlle.da.backend.entities;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "friend", nullable = false)
    private UserEntity friend;

    private LocalDate friendsSince;

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

    public LocalDate getFriendsSince() {
        return friendsSince;
    }

    public void setFriendsSince(LocalDate friendsSince) {
        this.friendsSince = friendsSince;
    }
}
