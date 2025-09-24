package co.edu.uceva.dogservice.controllers;

import co.edu.uceva.dogservice.model.entities.Dog;
import co.edu.uceva.dogservice.model.service.IDogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producto-service")
public class DogRestController {


    private IDogService dogService;

    @Autowired
    public DogRestController(IDogService dogService) {
        this.dogService = dogService;
    }


    @GetMapping("/Dog")
    public List<Dog> getDog(){
        return dogService.findAll();
    }


    @GetMapping("/dog/page/{page}")
    public Page<Dog> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return dogService.findAll(pageable);
    }

    @PostMapping("/dog")
    public Dog save(@RequestBody Dog dog) {
        return dogService.save(dog);
    }


    @DeleteMapping("/dog")
    public void delete(@RequestBody Dog dog){
        dogService.delete(dog);
    }


    @PutMapping("/dog")
    public Dog update(@RequestBody Dog dog){
        return dogService.update(dog);
    }


    @GetMapping("/dog/{id}")
    public Dog findById(@PathVariable("id") Long id){
        return dogService.findById(id);
    }


}