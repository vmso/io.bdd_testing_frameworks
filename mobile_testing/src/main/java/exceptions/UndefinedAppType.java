package exceptions;

import enums.AppType;

public class UndefinedAppType extends RuntimeException {
    public UndefinedAppType(AppType appType) {
        super("Undefined app type " + appType);
    }
}
