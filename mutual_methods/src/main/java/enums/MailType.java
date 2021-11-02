package enums;

public enum MailType {
    HOTMAIL("@hotmail.com"),
    GMAIL("@gmail.com"),
    YAHOO("@yahoo.com"),
    OUTLOOK("@outlook.com");

    public final String extension;

    MailType(String mailType) {
        extension = mailType;
    }
}
