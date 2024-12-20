package at.htlle.da.backend.services;

import at.htlle.da.backend.dtos.FriendDTO;
import at.htlle.da.backend.dtos.FriendRequestDTO;
import at.htlle.da.backend.entities.FriendRequest;
import at.htlle.da.backend.entities.Friend;
import at.htlle.da.backend.entities.UserEntity;
import at.htlle.da.backend.repositories.FriendRequestRepository;
import at.htlle.da.backend.repositories.FriendRepository;
import at.htlle.da.backend.repositories.UserRepository;
import org.apache.catalina.User;
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
                .orElseThrow();
        UserEntity receiver = userRepository.findByUsername(receiverUsername)
                .orElseThrow();


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
                .orElseThrow();
        UserEntity receiver = userRepository.findById(receiverEmail)
                .orElseThrow();

        FriendRequest request = friendRequestRepository.findBySenderAndReceiver(sender, receiver)
                .orElseThrow();
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
        return friendRequestRepository.findBySender(sendingUser)
                .stream()
                .map(fr -> new FriendRequestDTO(fr.getReceiver().getUsername(), fr.getSendingDate())).toList();
    }

    public List<FriendRequestDTO> getAllIncomingFriendRequests(String email) {
        UserEntity receivingUser = userRepository.findById(email).orElseThrow();
        return friendRequestRepository.findByReceiver(receivingUser)
                .stream()
                .map(fr -> new FriendRequestDTO(fr.getSender().getUsername(), fr.getSendingDate())).toList();
    }

    public void deleteFriendship(String masterEmail, String slaveUsername) {
        UserEntity masterUser = userRepository.findById(masterEmail).orElseThrow();
        UserEntity slaveUser = userRepository.findByUsername(slaveUsername).orElseThrow();
        Friend masterFriendship = friendRepository.findFriendByUserAndFriend(masterUser, slaveUser).orElseThrow();
        Friend slaveFriendship = friendRepository.findFriendByUserAndFriend(slaveUser, masterUser).orElseThrow();

        masterUser.getFriends().remove(masterFriendship);
        slaveUser.getFriends().remove(slaveFriendship);

        friendRepository.delete(masterFriendship);
        friendRepository.delete(slaveFriendship);


    }

}
