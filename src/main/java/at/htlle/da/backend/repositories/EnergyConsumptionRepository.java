package at.htlle.da.backend.repositories;

import at.htlle.da.backend.entities.calculations.Diet;
import at.htlle.da.backend.entities.calculations.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, String> {
}
