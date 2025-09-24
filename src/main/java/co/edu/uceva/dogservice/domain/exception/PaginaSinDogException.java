package co.edu.uceva.dogservice.domain.exception;

public class PaginaSinDogException extends RuntimeException {
    public PaginaSinDogException(int page) {
        super("No hay dog en la página solicitada: " + page);
    }
}
