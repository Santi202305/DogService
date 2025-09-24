package co.edu.uceva.dogservice.domain.repository;

import co.edu.uceva.dogservice.domain.model.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDogRepository extends JpaRepository<Dog, Long> {
}
