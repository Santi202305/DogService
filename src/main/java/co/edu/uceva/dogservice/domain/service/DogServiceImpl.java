package co.edu.uceva.dogservice.domain.service;

import co.edu.uceva.dogservice.domain.model.Dog;
import co.edu.uceva.dogservice.domain.repository.IDogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DogServiceImpl implements IDogService {

    IDogRepository dogRepository;

    public DogServiceImpl(IDogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    @Transactional
    public Dog save(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    @Transactional
    public void delete(Dog dog) {
        dogRepository.delete(dog);
    }

    @Override
    @Transactional(readOnly = true)
    public Dog findById(Long id) {
        return dogRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Dog update(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dog> findAll() {
        return (List<Dog>) dogRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Dog> findAll(Pageable pageable) {
        return dogRepository.findAll(pageable);
    }
}


