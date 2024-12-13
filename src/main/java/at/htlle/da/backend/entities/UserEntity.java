package at.htlle.da.backend.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private String email;
    @Column(unique = true)
    private String userName;
    private String firstName;
    private String lastName;
    private String profilePictureHash;


    public UserEntity() {
    }

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendRequest> receiver = new HashSet<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendRequest> sender = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Friend> friends = new HashSet<>();

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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Friend> getFriends() {
        return friends;
    }


    public Set<FriendRequest> getReceiver() {
        return receiver;
    }


    public Set<FriendRequest> getSender() {
        return sender;
    }


    public String getProfilePictureHash() {
        return profilePictureHash;
    }

    public void setProfilePictureHash(String profilePicture) {
        this.profilePictureHash = profilePicture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public void setReceiver(Set<FriendRequest> receiver) {
        this.receiver = receiver;
    }

    public void setSender(Set<FriendRequest> sender) {
        this.sender = sender;
    }

}
