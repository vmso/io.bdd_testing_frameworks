package base;

public class GetFileName {


    private String fileName;

    private static GetFileName instances;

    private GetFileName() {

    }

    public static GetFileName getInstance() {
        if (instances == null)
            instances = new GetFileName();
        return instances;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
