package enums;

/**
 * Enum representing switch targets for browser context switching.
 */
public enum SwitchTarget {
    ALERT("alert"),
    DEFAULT_WINDOW("defaultWindow"),
    ACTIVE_ELEMENT("activeElement");

    private final String value;

    SwitchTarget(String value) {
        this.value = value;
    }

    /**
     * Gets the string value of the switch target.
     * @return switch target as string
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
