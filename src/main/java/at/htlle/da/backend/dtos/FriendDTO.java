package at.htlle.da.backend.dtos;

import java.time.LocalDate;

public class FriendDTO {
    private String username;
    private LocalDate friendsSince;

    public FriendDTO(String username, LocalDate friendsSince) {
        this.username = username;
        this.friendsSince = friendsSince;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getFriendsSince() {
        return friendsSince;
    }

    public void setFriendsSince(LocalDate friendsSince) {
        this.friendsSince = friendsSince;
    }
}
