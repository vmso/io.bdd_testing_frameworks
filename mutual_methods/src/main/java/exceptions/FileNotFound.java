package exceptions;

public class FileNotFound extends RuntimeException {
    public FileNotFound(String fileName) {
        super(String.format("The file \"%s\" is not founded in project classpath.", fileName));
    }
}
