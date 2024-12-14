package at.htlle.da.backend.dtos;

public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private byte[] profilePictureHash;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public byte[] getProfilePictureHash() {
        return profilePictureHash;
    }

    public void setProfilePictureHash(byte[] profilePictureHash) {
        this.profilePictureHash = profilePictureHash;
    }

}