package at.htlle.da.backend.repositories;

import at.htlle.da.backend.entities.calculations.Diet;
import at.htlle.da.backend.entities.calculations.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteRepository extends JpaRepository<Waste, String> {
}
