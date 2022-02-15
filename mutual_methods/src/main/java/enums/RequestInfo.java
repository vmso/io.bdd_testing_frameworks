package enums;

public enum RequestInfo {
    REQUEST("request"),
    RESPONSE("response");

    public final String info;

    RequestInfo(String value) {
        this.info = value;
    }
}
