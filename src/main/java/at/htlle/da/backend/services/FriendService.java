package at.htlle.da.backend.services;

import at.htlle.da.backend.dtos.FriendDTO;
import at.htlle.da.backend.dtos.FriendRequestDTO;
import at.htlle.da.backend.entities.FriendRequest;
import at.htlle.da.backend.entities.Friend;
import at.htlle.da.backend.entities.UserEntity;
import at.htlle.da.backend.repositories.FriendRequestRepository;
import at.htlle.da.backend.repositories.FriendRepository;
import at.htlle.da.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FriendService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendRequestRepository friendRequestRepository;
    @Autowired
    private FriendRepository friendRepository;

    public void sendRequest(String senderEmail, String receiverUsername) {
        UserEntity sender = userRepository.findById(senderEmail)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found: " + senderEmail));
        UserEntity receiver = userRepository.findByUsername(receiverUsername)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found: " + receiverUsername));


        if (friendRequestRepository.existsBySenderAndReceiver(sender, receiver)) {
            throw new IllegalStateException("Friendrequest already sent");
        }

        FriendRequest request = new FriendRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        request.setSendingDate(LocalDate.now());
        sender.getSender().add(request);
        receiver.getReceiver().add(request);
        friendRequestRepository.save(request);
    }

    public void acceptRequest(String senderUserName, String receiverEmail) {
        UserEntity sender = userRepository.findByUsername(senderUserName)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found: " + senderUserName));
        UserEntity receiver = userRepository.findById(receiverEmail)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found: " + receiverEmail));

        FriendRequest request = friendRequestRepository.findBySenderAndReceiver(sender, receiver)
                .orElseThrow(() -> new IllegalStateException("Friend request not found"));
        Friend senderFriend = new Friend();
        senderFriend.setUser(sender);
        senderFriend.setFriend(receiver);
        senderFriend.setFriendsSince(LocalDate.now());
        Friend receiverFriend = new Friend();
        receiverFriend.setUser(receiver);
        receiverFriend.setFriend(sender);
        receiverFriend.setFriendsSince(LocalDate.now());
        sender.getFriends().add(senderFriend);
        receiver.getFriends().add(receiverFriend);
        friendRepository.save(senderFriend);
        friendRepository.save(receiverFriend);
        userRepository.save(sender);
        userRepository.save(receiver);

        friendRequestRepository.delete(request);
    }

    public List<FriendDTO> getAllFriends(String email) {
        UserEntity user = userRepository.findById(email).orElseThrow();
        return user.getFriends()
                .stream()
                .map(fr -> new FriendDTO(fr.getFriend().getUsername(), fr.getFriendsSince())).toList();
    }

    public List<FriendRequestDTO> getAllSendingFriendRequests(String email) {
        UserEntity sendingUser = userRepository.findById(email).orElseThrow();
        return friendRequestRepository.findByReceiver(sendingUser)
                .stream()
                .map(fr -> new FriendRequestDTO(fr.getReceiver().getUsername(), fr.getSendingDate())).toList();
    }

    public List<FriendRequestDTO> getAllIncomingFriendRequests(String email) {
        UserEntity receivingUser = userRepository.findById(email).orElseThrow();
        return friendRequestRepository.findByReceiver(receivingUser)
                .stream()
                .map(fr -> new FriendRequestDTO(fr.getSender().getUsername(), fr.getSendingDate())).toList();
    }

}
