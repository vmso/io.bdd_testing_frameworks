package exceptions;

public class KeywordNotFound extends Exception {
    final String message;

    public KeywordNotFound(String keyword, String fileName) {

        this.message = String.format("%s keyword is not found in %s.json file please check your keyword.", keyword, fileName);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
