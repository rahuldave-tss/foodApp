package exceptions;

public class NoOrdersFoundException extends RuntimeException {
    public NoOrdersFoundException() {
        super("No orders found !!");
    }
}
