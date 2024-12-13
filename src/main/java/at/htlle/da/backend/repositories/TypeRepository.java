package at.htlle.da.backend.repositories;

import at.htlle.da.backend.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, String> {
}
