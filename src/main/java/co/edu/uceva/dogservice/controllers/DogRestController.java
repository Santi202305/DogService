package co.edu.uceva.dogservice.controllers;

import co.edu.uceva.dogservice.model.entities.Dog;
import co.edu.uceva.dogservice.model.repository.IDogRepository;
import co.edu.uceva.dogservice.model.service.IDogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dog-service")
public class DogRestController {

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    private IDogService dogService;

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    @Autowired
    public DogRestController(IDogService dogService) {
        this.dogService = dogService;
    }

    // Metodo que retorna todos los productos
    @GetMapping("/dog")
    public List<Dog> getDog(){
        return dogService.findAll();
    }

    // Metodo que guarda un producto pasandolo por el cuerpo de la petición
    @PostMapping("/dog")
    public Dog save(@RequestBody Dog dog) {
        return dogService.save(dog);
    }

    // Metodo que elimina un producto pasandolo por el cuerpo de la petición
    @DeleteMapping("/dog")
    public void delete(@RequestBody Dog dog){
        dogService.delete(dog);
    }

    // Metodo que actualiza un producto pasandolo por el cuerpo de la petición
    @PutMapping("/dog")
    public Dog update(@RequestBody Dog producto){
        return dogService.update(producto);
    }

    // Metodo que retorna un producto por su id pasado por la URL
    @GetMapping("/dog/{id}")
    public Dog findById(@PathVariable("id") Long id){
        return dogService.findById(id);
    }


}
