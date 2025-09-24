package co.edu.uceva.dogservice.controllers;

import co.edu.uceva.dogservice.model.entities.Dog;
import co.edu.uceva.dogservice.model.service.IDogService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dog-service")
public class DogRestController {


    private final IDogService dogService;

    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String DOG = "dog";
    private static final String DOGS = "dogs";


    public DogRestController(IDogService dogService) {
        this.dogService = dogService;
    }


    @GetMapping("/dog")
    public ResponseEntity<Map<String, Object>> getDog() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Dog> dog = dogService.findAll();

            if (dog.isEmpty()) {
                response.put(MENSAJE, "No hay dogs en la base de datos.");
                response.put(DOGS, dog);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

            response.put(DOGS, dog);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/dog/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);

        try {
            Page<Dog> productos = dogService.findAll(pageable);

            if (productos.isEmpty()) {
                response.put(MENSAJE, "No hay dog en la página solicitada.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            return ResponseEntity.ok(productos);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (IllegalArgumentException e) {
            response.put(MENSAJE, "Número de página inválido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @PostMapping("/dog")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Dog dog) {
        Map<String, Object> response = new HashMap<>();

        try {

            Dog nuevoDog = dogService.save(dog);

            response.put(MENSAJE, "El dog ha sido creado con éxito!");
            response.put(DOG, nuevoDog);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al insertar el dog en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/dog")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Dog dog) {
        Map<String, Object> response = new HashMap<>();
        try {
            Dog dogExistente = dogService.findById(dog.getId());
            if (dogExistente == null) {
                response.put(MENSAJE, "El producto ID: " + dog.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            dogService.delete(dog);
            response.put(MENSAJE, "El dog ha sido eliminado con éxito!");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al eliminar el dog de la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/dog")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Dog dog) {
        Map<String, Object> response = new HashMap<>();

        try {

            if (dogService.findById(dog.getId()) == null) {
                response.put(MENSAJE, "Error: No se pudo editar, el dog ID: " + dog.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }


            Dog dogActualizado = dogService.save(dog);

            response.put(MENSAJE, "El dog ha sido actualizado con éxito!");
            response.put(DOG, dogActualizado);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al actualizar el producto en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/dog/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Dog dog = dogService.findById(id);

            if (dog == null) {
                response.put(MENSAJE, "El dog ID: " + id + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put(MENSAJE, "El dog ha sido actualizado con éxito!");
            response.put(DOG, dog);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
