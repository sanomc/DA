package at.htlle.da.backend.controller;


import at.htlle.da.backend.entities.Friend;
import at.htlle.da.backend.entities.FriendRequest;
import at.htlle.da.backend.entities.UserEntity;
import at.htlle.da.backend.services.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

    @Autowired
    private FriendRequestService friendRequestService;

    @PostMapping("/send")
    public String sendFriendRequest(@RequestParam String receiverUserName, @AuthenticationPrincipal Jwt principal) {
        friendRequestService.sendRequest(principal.getClaim("email"), receiverUserName);
        return "Friend request sent";
    }


    @PostMapping("/accept")
    public String acceptFriendRequest(@RequestParam String senderUserName, @AuthenticationPrincipal Jwt principal) {
        friendRequestService.acceptRequest(senderUserName, principal.getClaim("email"));
        return "Friend request accepted";
    }

    @GetMapping("/all-friends")
    public Set<Friend> getAllFriends(@AuthenticationPrincipal Jwt principal) {
        return friendRequestService.getAllFriends(principal.getClaim("email"));
    }


    @GetMapping("/all-received-requests")
    public Set<FriendRequest> getAllReceivedFriendRequests(@AuthenticationPrincipal Jwt principal) {
        return friendRequestService.getAllReceivingFriendRequests(principal.getClaim("email"));
    }


    @GetMapping("/all-sent-requests")
    public Set<FriendRequest> getAllSentFriendRequests(@AuthenticationPrincipal Jwt principal) {
        return friendRequestService.getAllSendingFriendRequests(principal.getClaim("email"));
    }
}
