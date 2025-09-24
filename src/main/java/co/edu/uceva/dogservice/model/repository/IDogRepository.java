package co.edu.uceva.dogservice.model.repository;

import co.edu.uceva.dogservice.model.entities.Dog;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface que hereda de CrudRepository para realizar las
 * operaciones de CRUD sobre la entidad Producto
 */
public interface IDogRepository extends CrudRepository<Dog, Long> {
}
