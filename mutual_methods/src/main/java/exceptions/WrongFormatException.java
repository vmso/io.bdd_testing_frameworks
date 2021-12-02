package exceptions;

public class WrongFormatException extends RuntimeException {
    final String message;

    public WrongFormatException(Object firstElmType, Object secondElmType) {
        String firstType = firstElmType.getClass().getSimpleName();
        String secondType = secondElmType.getClass().getSimpleName();
        this.message = "The objects which is trying to compare arent same type" +
                "" + firstElmType + "'s type ise " + firstType + " " +
                "" + secondElmType + "'s type ise " + secondType + " " +
                " They cant be compare";
    }

    @Override
    public String getMessage() {
        return message;
    }

}
