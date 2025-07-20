package enums;

/**
 * Enum representing supported browser types for web testing.
 */
public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox"),
    OPERA("opera"),
    SAFARI("safari"),
    EDGE("edge"),
    MOCK("mock");

    private final String value;

    Browser(String value) {
        this.value = value;
    }

    /**
     * Gets the string value of the browser.
     * @return browser name as string
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

