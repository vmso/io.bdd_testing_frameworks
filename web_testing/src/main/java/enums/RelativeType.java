package enums;

/**
 * Enum representing relative locator types for Selenium's relative locators.
 */
public enum RelativeType {
    ABOVE("above"),
    BELOW("below"),
    NEAR("near"),
    RIGHT_OF("rightOf"),
    LEFT_OF("leftOf");

    private final String value;

    RelativeType(String value) {
        this.value = value;
    }

    /**
     * Gets the string value of the relative type.
     * @return relative type as string
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
