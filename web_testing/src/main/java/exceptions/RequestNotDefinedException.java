package exceptions;

/**
 * Thrown when a request is not defined before an operation that requires it.
 */
public class RequestNotDefinedException extends RuntimeException {
    public RequestNotDefinedException() {
        super("To run this method, please first define a new request with the defineNewRequest() method.");
    }
}
