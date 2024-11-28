package at.htlle.da.backend.repositories;

import at.htlle.da.backend.entities.FriendRequest;
import at.htlle.da.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    Set<FriendRequest> findBySender(UserEntity sender);

    Set<FriendRequest> findByReceiver(UserEntity receiver);

    Optional<FriendRequest> findBySenderAndReceiver(UserEntity sender, UserEntity receiver);

    boolean existsBySenderAndReceiver(UserEntity sender, UserEntity receiver);

}
