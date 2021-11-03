package exceptions;

public class FileNotFounded extends Exception {
    public FileNotFounded(String fileName) {
        super(String.format("The file \"%s\" is not founded in project classpath.", fileName));
    }
}
