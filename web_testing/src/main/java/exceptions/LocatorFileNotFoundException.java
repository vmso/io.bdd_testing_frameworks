package exceptions;

/**
 * Thrown when a locator file cannot be found in project resources.
 */
public class LocatorFileNotFoundException extends RuntimeException {
    public LocatorFileNotFoundException(String fileName) {
        super(String.format("Locator file couldn't be found in project resources. Filename: %s. Please create a locator folder and add your locator JSON file.", fileName));
    }
}
