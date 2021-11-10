package exceptions;

public class NoSuchSelector extends Exception{

    final String  message;

    public NoSuchSelector(String selector) {

        this.message = "The selector type you send is undefined, selectorType: " + selector;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
