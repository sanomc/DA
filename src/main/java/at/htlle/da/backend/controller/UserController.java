package at.htlle.da.backend.controller;

import at.htlle.da.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public void registerUser(@AuthenticationPrincipal Jwt principal) {
        String email = principal.getClaim("email"); // E-Mail direkt aus den JWT-Claims
        String firstName = principal.getClaim("given_name");
        String lastName = principal.getClaim("family_name");
        String profilePicture = principal.getClaim("picture");

        userService.registerUser(email, firstName, lastName, profilePicture);
    }

    @GetMapping("/profile-picture")
    public String getUserProfilePicture(@AuthenticationPrincipal Jwt principal) {

        return userService.getProfilePicture(principal.getClaim("email"));
    }
}


