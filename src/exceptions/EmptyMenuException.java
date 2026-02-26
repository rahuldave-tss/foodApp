package exceptions;

public class EmptyMenuException extends RuntimeException {
    public EmptyMenuException(String message) {
        super(message);
    }
}
