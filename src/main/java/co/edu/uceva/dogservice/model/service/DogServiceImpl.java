package co.edu.uceva.dogservice.model.service;

import co.edu.uceva.dogservice.model.entities.Dog;
import co.edu.uceva.dogservice.model.repository.IDogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase que implementa los métodos de la interfaz IProductoService
 * para realizar las operaciones de negocio sobre la entidad Producto
 */
@Service
public class DogServiceImpl implements IDogService {

    IDogRepository dogRepository;

    public DogServiceImpl(IDogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public Dog save(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public void delete(Dog dog) {
        dogRepository.delete(dog);
    }

    @Override
    public Dog findById(Long id) {
        return dogRepository.findById(id).orElse(null);
    }

    @Override
    public Dog update(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public List<Dog> findAll() {
        return (List<Dog>) dogRepository.findAll();
    }
}

