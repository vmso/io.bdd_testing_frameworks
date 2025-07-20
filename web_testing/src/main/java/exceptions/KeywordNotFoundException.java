package exceptions;

/**
 * Thrown when a keyword is not found in the locator JSON file.
 */
public class KeywordNotFoundException extends RuntimeException {
    public KeywordNotFoundException(String keyword, String jsonKey) {
        super(String.format("\"%s\" keyword is not found with \"%s\" jsonKey in locator JSON file. Please check your keyword.", keyword, jsonKey));
    }
}
