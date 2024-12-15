package at.htlle.da.backend.controller;


import at.htlle.da.backend.dtos.FriendDTO;
import at.htlle.da.backend.dtos.FriendRequestDTO;
import at.htlle.da.backend.services.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @PostMapping("/send/{username}")
    public ResponseEntity<String> sendFriendRequest(@PathVariable String username, @AuthenticationPrincipal Jwt principal) {
        friendService.sendRequest(principal.getClaim("email"), username);
        return ResponseEntity.ok("Friend request sent!");
    }


    @PostMapping("/accept/{username}")
    public ResponseEntity<String> acceptFriendRequest(@PathVariable String username, @AuthenticationPrincipal Jwt principal) {
        friendService.acceptRequest(username, principal.getClaim("email"));
        return ResponseEntity.ok("Friend request accepted!");
    }

    @GetMapping
    public ResponseEntity<List<FriendDTO>> getAllFriends(@AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(friendService.getAllFriends(principal.getClaim("email")));
    }


    @GetMapping("/incoming-requests")
    public ResponseEntity<List<FriendRequestDTO>> getAllReceivedFriendRequests(@AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(friendService.getAllIncomingFriendRequests(principal.getClaim("email")));
    }


    @GetMapping("/all-sent-requests")
    public ResponseEntity<List<FriendRequestDTO>> getAllSentFriendRequests(@AuthenticationPrincipal Jwt principal) {
        return ResponseEntity.ok(friendService.getAllSendingFriendRequests(principal.getClaim("email")));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> endFriendShip(@PathVariable String username, @AuthenticationPrincipal Jwt principal) {
        friendService.deleteFriendship(principal.getClaim("email"), username);
        return ResponseEntity.ok("Friend " + username + " removed!");
    }
}
