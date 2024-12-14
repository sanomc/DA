package at.htlle.da.backend.repositories;

import at.htlle.da.backend.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByUserEmailAndTimestampBetween(String email, LocalDateTime startTimestamp, LocalDateTime endTimeStamp);
}
