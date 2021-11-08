package enums;

public enum AppType {
    ANDROID("Android"),
    IOS("iOS");

    private String value;

    AppType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
