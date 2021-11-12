package exceptions;

public class LocatorFileNotFound extends Exception {

    final String message;

    public LocatorFileNotFound(String fileName) {

        this.message = String.format("Locator file couldn't find in project resources filename:%s\n " +
                "please create a locator folder which is called locator " +
                "and then create your locator JSON in that folder ", fileName);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
