package enums;

public enum Browsers {
    CHROME("chrome"),
    FIREFOX("firefox"),
    OPERA("opera"),
    SAFARI("safari"),
    EDGE("edge");
    private String browser;

    Browsers(String browser) {
        this.browser = browser;
    }

    public String getBrowser() {
        return this.browser;
    }
}

