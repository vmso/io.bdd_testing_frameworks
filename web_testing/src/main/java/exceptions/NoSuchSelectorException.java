package exceptions;

/**
 * Thrown when a selector type is undefined.
 */
public class NoSuchSelectorException extends RuntimeException {
    public NoSuchSelectorException(String selector) {
        super("The selector type you sent is undefined. selectorType: " + selector);
    }
}
