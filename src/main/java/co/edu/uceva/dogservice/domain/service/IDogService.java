package co.edu.uceva.dogservice.domain.service;

import co.edu.uceva.dogservice.domain.model.Dog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IDogService {
    Dog save(Dog dog);
    void delete(Dog dog);
    Dog findById(Long id);
    Dog update(Dog dog);
    List<Dog> findAll();
    Page<Dog> findAll(Pageable pageable);
}
