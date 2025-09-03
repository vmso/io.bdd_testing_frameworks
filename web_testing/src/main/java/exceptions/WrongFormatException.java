package exceptions;

/**
 * Thrown when two objects being compared are not of the same type.
 */
public class WrongFormatException extends RuntimeException {
    public WrongFormatException(Object firstElmType, Object secondElmType) {
        super("The objects being compared are not the same type: " +
                firstElmType + " is of type " + firstElmType.getClass().getSimpleName() + ", " +
                secondElmType + " is of type " + secondElmType.getClass().getSimpleName() + ". They cannot be compared.");
    }
}
