package cscie55.hw3.exception;

/**
 * An exception class for when elevator is full
 * @author Tofik Mussa
 */
public class ElevatorFullException extends Exception {
    public ElevatorFullException(String message) {
        super(message);
    }
}
