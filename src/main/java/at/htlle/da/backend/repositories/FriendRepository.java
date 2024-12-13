package at.htlle.da.backend.repositories;

import at.htlle.da.backend.entities.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, Long> {
}
