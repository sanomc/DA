package at.htlle.da.backend.services;

import at.htlle.da.backend.entities.FriendRequest;
import at.htlle.da.backend.entities.UserEntity;
import at.htlle.da.backend.repositories.FriendRequestRepository;
import at.htlle.da.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FriendRequestService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendRequestRepository friendRequestRepository;

    public void sendRequest(String senderEmail, String receiverEmail) {
        UserEntity sender = userRepository.findById(senderEmail)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found: " + senderEmail));
        UserEntity receiver = userRepository.findById(receiverEmail)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found: " + receiverEmail));


        if (friendRequestRepository.existsBySenderAndReceiver(sender, receiver)) {
            throw new IllegalStateException("Friendrequest already sent");
        }

        FriendRequest request = new FriendRequest();
        request.setSender(sender);
        request.setReceiver(receiver);
        sender.getSender().add(request);
        receiver.getReceiver().add(request);
        friendRequestRepository.save(request);
    }

    public void acceptRequest(String senderEmail, String receiverEmail) {
        UserEntity sender = userRepository.findById(senderEmail)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found: " + senderEmail));
        UserEntity receiver = userRepository.findById(receiverEmail)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found: " + receiverEmail));

        FriendRequest request = friendRequestRepository.findBySenderAndReceiver(sender, receiver)
                .orElseThrow(() -> new IllegalStateException("Friend request not found"));

        sender.getFriends().add(receiver);
        receiver.getFriends().add(sender);
        userRepository.save(sender);
        userRepository.save(receiver);

        friendRequestRepository.delete(request);
    }

    public Set<UserEntity> getAllFriends(String email) {
        UserEntity user = userRepository.findById(email).orElseThrow();
        return user.getFriends();
    }

    public Set<FriendRequest> getAllSendingFriendRequests(String email) {
        UserEntity sendingUser = userRepository.findById(email).orElseThrow();
        return friendRequestRepository.findBySender(sendingUser);
    }

    public Set<FriendRequest> getAllReceivingFriendRequests(String email) {
        UserEntity receivingUser = userRepository.findById(email).orElseThrow();
        return friendRequestRepository.findByReceiver(receivingUser);
    }

}
