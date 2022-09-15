package enums;

public enum AppType {
    ANDROID("Android"),
    IOS("iOS"),
    MIC("mic");

    private final String value;

    AppType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
