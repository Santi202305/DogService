package co.edu.uceva.dogservice.model.repository;

import co.edu.uceva.dogservice.model.entities.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDogRepository extends JpaRepository<Dog, Long> {
}
