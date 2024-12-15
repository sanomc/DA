package at.htlle.da.backend.repositories;

import at.htlle.da.backend.entities.Friend;
import at.htlle.da.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findFriendByUserAndFriend(UserEntity masterUser, UserEntity slaveUser);
    List<Friend> findByUser(UserEntity user);
}
