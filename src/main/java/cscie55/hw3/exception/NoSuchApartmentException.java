package cscie55.hw3.exception;

/**
 * Exception thrown when an apartment doesn't exist
 * @author Tofik Mussa
 */
public class NoSuchApartmentException extends Exception {
    public NoSuchApartmentException(String message) {
        super(message);
    }
}
