package enums;

/**
 * Enum representing locator strategies for finding web elements.
 */
public enum LocatorType {
    ID("id"),
    CSS_SELECTOR("cssSelector"),
    XPATH("xpath"),
    NAME("name"),
    CLASS_NAME("className"),
    LINK_TEXT("linkText"),
    PARTIAL_LINK_TEXT("partialLinkText"),
    TAG_NAME("tagName"),
    TEXT("text");

    private final String value;

    LocatorType(String value) {
        this.value = value;
    }

    /**
     * Gets the string value of the locator type.
     * @return locator type as string
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

