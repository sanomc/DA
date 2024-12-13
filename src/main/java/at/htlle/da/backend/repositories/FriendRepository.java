package at.htlle.da.backend.repositories;

import at.htlle.da.backend.entities.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, Long> {
}
