package exceptions;

import enums.Browsers;

public class UndefinedBrowserType extends RuntimeException{
    public UndefinedBrowserType(Browsers browsers) {
        super("Undefined app type " + browsers);
    }
}
