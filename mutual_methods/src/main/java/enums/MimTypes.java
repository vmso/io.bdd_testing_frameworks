package enums;

public enum MimTypes {
    APNG("image/apng"),
    AVIF("image/avif"),
    CSV("text/csv"),
    DOC("application/msword"),
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    FLIF("image/flif"),
    JFIF("image/jpeg"),
    JPEG("image/jpeg"),
    JPG("image/jpeg"),
    JS("application/javascript"),
    JSON("application/json"),
    JXL("image/jxl"),
    ODT("application/vnd.oasis.opendocument.text"),
    PDF("application/pdf"),
    PJP("image/jpeg"),
    PJPEG("image/jpeg"),
    PNG("image/png"),
    PPT("application/vnd.ms-powerpoint"),
    PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation"),
    SQL("application/sql"),
    SVG("image/svg+xml"),
    TXT("text/plain"),
    WEBP("image/webp"),
    XLS("application/vnd.ms-excel"),
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    XML("text/xml");

    private final String mimType;

    MimTypes(String value) {
        this.mimType = value;
    }

    public String getText() {
        return mimType;
    }

}
