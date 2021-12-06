package enums;

public enum SwitchEnums {
    ALERT("allert"),
    DEFAULT_WINDOW("defaultWindow"),
    ACTIVE_ELEMENT("activeElement");

    private final String value;

    SwitchEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
