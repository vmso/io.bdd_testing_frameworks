package exceptions;

public class KeywordNotFound extends RuntimeException {
    final String message;

    public KeywordNotFound(String keyword, String jsonKey) {

        this.message = String.format("\"%s\" keyword is not found with \"%s\" jsonKey in locator json file please check your keyword.", keyword, jsonKey);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
