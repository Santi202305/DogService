package co.edu.uceva.dogservice.delivery.rest;

import co.edu.uceva.dogservice.domain.model.Dog;
import co.edu.uceva.dogservice.domain.service.IDogService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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


    @GetMapping("/dogs")
    public ResponseEntity<Map<String, Object>> getDogs() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Dog> dogs = dogService.findAll();

            if (dogs.isEmpty()) {
                response.put(MENSAJE, "No hay dogs en la base de datos.");
                response.put(DOGS, dogs); // siempre devuelve el campo "dogs"
                return ResponseEntity.ok(response); // 200 pero lista vacía
            }

            response.put(DOGS, dogs);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ")
                    .concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/dog/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);

        try {
            Page<Dog> dogs = dogService.findAll(pageable);

            if (dogs.isEmpty()) {
                response.put(MENSAJE, "No hay dogs en la página solicitada.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            return ResponseEntity.ok(dogs);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ")
                    .concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (IllegalArgumentException e) {
            response.put(MENSAJE, "Número de página inválido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }


    @PostMapping("/dogs")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Dog dog, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();

            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            Dog nuevoDog = dogService.save(dog);

            response.put(MENSAJE, "El dog ha sido creado con éxito!");
            response.put(DOG, nuevoDog);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al insertar el dog en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ")
                    .concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/dogs")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Dog dog) {
        Map<String, Object> response = new HashMap<>();
        try {
            Dog dogExistente = dogService.findById(dog.getId());
            if (dogExistente == null) {
                response.put(MENSAJE, "El dog ID: " + dog.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            dogService.delete(dog);
            response.put(MENSAJE, "El dog ha sido eliminado con éxito!");
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al eliminar el dog de la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ")
                    .concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/dogs")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Dog dog, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();

            response.put("errors", errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

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
            response.put(MENSAJE, "Error al actualizar el dog en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ")
                    .concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Obtener un dog por su ID.
     */
    @GetMapping("/dogs/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Dog dog = dogService.findById(id);

            if (dog == null) {
                response.put(MENSAJE, "El dog ID: " + id + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put(MENSAJE, "El dog ha sido encontrado con éxito!");
            response.put(DOG, dog);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ")
                    .concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
