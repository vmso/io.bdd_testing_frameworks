package exceptions;

import enums.AppType;

public class UndefinedAppType extends Exception {
    public UndefinedAppType(AppType appType) {
        super("Undefined app type " + appType);
    }
}
