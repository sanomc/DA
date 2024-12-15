package at.htlle.da.backend.controller;

import at.htlle.da.backend.dtos.UserDTO;
import at.htlle.da.backend.entities.UserEntity;
import at.htlle.da.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<UserEntity> getUser(@AuthenticationPrincipal Jwt principal) {
        String email = principal.getClaim("email");
        String firstName = principal.getClaim("given_name");
        String lastName = principal.getClaim("family_name");
        String profilePicture = principal.getClaim("picture");

        return ResponseEntity.ok(userService.getUser(email, firstName, lastName, profilePicture));
    }

    @PutMapping("")
    public ResponseEntity<UserEntity> editUser(@RequestBody UserDTO userDTO, @AuthenticationPrincipal Jwt principal){
        return ResponseEntity.ok(userService.updateUser(principal.getClaim("email"), userDTO));
    }
}


