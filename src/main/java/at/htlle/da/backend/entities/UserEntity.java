package at.htlle.da.backend.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicture;

    @ManyToMany
    @JoinTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )

    private Set<UserEntity> friends = new HashSet<>();

    public UserEntity() {
    }

    public void addFriend(UserEntity user) {
        if (this.friends.add(user)) {
            user.getFriends().add(this);
        }
    }

    public void removeFriend(UserEntity user) {
        if (this.friends.remove(user)) {
            user.getFriends().remove(this);
        }
    }

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendRequest> receiver = new HashSet<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendRequest> sender = new HashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<UserEntity> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserEntity> friends) {
        this.friends = friends;
    }

    public Set<FriendRequest> getReceiver() {
        return receiver;
    }

    public void setReceiver(Set<FriendRequest> receiver) {
        this.receiver = receiver;
    }

    public Set<FriendRequest> getSender() {
        return sender;
    }

    public void setSender(Set<FriendRequest> sender) {
        this.sender = sender;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
