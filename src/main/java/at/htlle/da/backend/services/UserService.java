package at.htlle.da.backend.services;

import at.htlle.da.backend.entities.UserEntity;
import at.htlle.da.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(String email, String firstName, String lastName, String profilePicture) {
        if(!userRepository.existsById(email)) {
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setProfilePictureHash(profilePicture);
            userRepository.save(user);
        }

    }

    public String getProfilePicture(String email) {
        return userRepository.getReferenceById(email).getProfilePictureHash();
    }
}
