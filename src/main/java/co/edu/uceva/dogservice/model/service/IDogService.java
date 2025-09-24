package co.edu.uceva.dogservice.model.service;

import co.edu.uceva.dogservice.model.entities.Dog;

import java.util.List;

/**
 * Interface que define los métodos que se pueden realizar sobre la entidad Producto
 */
public interface IDogService {
    Dog save(Dog dog);
    void delete(Dog dog);
    Dog findById(Long id);
    Dog update(Dog dog);
    List<Dog> findAll();
}
