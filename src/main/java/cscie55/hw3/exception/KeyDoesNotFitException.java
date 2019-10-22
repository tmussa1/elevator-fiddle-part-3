package cscie55.hw3.exception;

/**
 * Exception thrown when an unauthorized person tries to get into a room
 * @author Tofik Mussa
 */
public class KeyDoesNotFitException extends Exception  {
    public KeyDoesNotFitException(String message) {
        super(message);
    }
}
