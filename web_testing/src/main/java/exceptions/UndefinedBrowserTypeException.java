package exceptions;

import enums.Browser;

/**
 * Thrown when an undefined browser type is encountered.
 */
public class UndefinedBrowserTypeException extends RuntimeException {
    public UndefinedBrowserTypeException(Browser browser) {
        super("Undefined browser type: " + browser);
    }
}
