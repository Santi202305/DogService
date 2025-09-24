package co.edu.uceva.dogservice.domain.exception;

public class DogNoEncontradoException extends RuntimeException {
    public DogNoEncontradoException(Long id) {
        super("El dog con ID " + id + " no fue encontrado.");
    }
}
