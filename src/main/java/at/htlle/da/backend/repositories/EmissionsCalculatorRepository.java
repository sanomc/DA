package at.htlle.da.backend.repositories;

import at.htlle.da.backend.entities.EmissionsCalculator;
import at.htlle.da.backend.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmissionsCalculatorRepository extends JpaRepository<EmissionsCalculator, Long> {
    Optional<EmissionsCalculator> findByUserAndCalendarWeek(UserEntity user, String calendarWeek);
}
