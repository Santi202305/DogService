package co.edu.uceva.dogservice.domain.exception;

public class NoHayDogException extends RuntimeException {
  public NoHayDogException(String message) {
    super(message);
  }
}
