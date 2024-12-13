package at.htlle.da.backend.dtos;

import java.time.LocalDate;

public class FriendRequestDTO {

    private String fromUser;
    private LocalDate sentOn;

    public FriendRequestDTO(String user, LocalDate sentOn) {
        this.fromUser = user;
        this.sentOn = sentOn;
    }

    public String getUser() {
        return fromUser;
    }

    public void setUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public LocalDate getSentOn() {
        return sentOn;
    }

    public void setSentOn(LocalDate sentOn) {
        this.sentOn = sentOn;
    }
}

