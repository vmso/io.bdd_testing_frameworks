package exceptions;

/**
 * Thrown when a selector does not have any value.
 */
public class NullValueException extends RuntimeException {
    public NullValueException(String selector) {
        super("The selector you sent does not have any value. Selector: " + selector);
    }
}
