package exceptions;

/**
 * Thrown when a response is unexpectedly null.
 */
public class NullResponseException extends RuntimeException {
    public NullResponseException() {
        super("Response is null. Please create an API request first.");
    }
}