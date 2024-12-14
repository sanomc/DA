package at.htlle.da.backend.services;

import at.htlle.da.backend.dtos.UserDTO;
import at.htlle.da.backend.entities.UserEntity;
import at.htlle.da.backend.repositories.UserRepository;
import at.htlle.da.backend.util.ImageUrlToBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity getUser(String email, String firstName, String lastName, String profilePicture){

        if(userRepository.existsById(email)) {
            return userRepository.findById(email).orElseThrow();
        }
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setProfilePictureHash(ImageUrlToBase64.getInstance().convert(profilePicture));
        user.setAccountCreated(LocalDate.now());
        userRepository.save(user);
        return user;
    }

    public UserEntity updateUser(String email, UserDTO userDTO) {
        UserEntity user = userRepository.findById(email).orElseThrow();
        user.setUsername(userDTO.getUsername() != null ? userDTO.getUsername() : user.getUsername());
        user.setFirstName(userDTO.getFirstName() != null ? userDTO.getFirstName() : user.getFirstName());
        user.setLastName(userDTO.getLastName() != null ? userDTO.getLastName() : user.getLastName());
        user.setProfilePictureHash(userDTO.getProfilePictureHash() != null ? userDTO.getProfilePictureHash() : user.getProfilePictureHash());
        userRepository.save(user);
        return user;
    }


}
