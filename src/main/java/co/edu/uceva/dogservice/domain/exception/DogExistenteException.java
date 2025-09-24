package co.edu.uceva.dogservice.domain.exception;

public class DogExistenteException extends RuntimeException {
    public DogExistenteException(String nombre) {
        super("El Dog con nombre '" + nombre + "' ya existe.");
    }
}
