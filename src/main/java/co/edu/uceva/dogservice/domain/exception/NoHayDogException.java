package co.edu.uceva.dogservice.domain.exception;

public class NoHayDogException extends RuntimeException {
    public NoHayDogException() {
        super("No hay dog en la base de datos.");
    }
}