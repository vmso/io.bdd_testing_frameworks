package exceptions;

public class RequestNotDefined extends Exception {
    public RequestNotDefined() {
        super("To run this method, please first define a new request with the define newRequest() method.");
    }
}
