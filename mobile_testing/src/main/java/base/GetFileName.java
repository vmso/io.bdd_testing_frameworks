package base;

import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.ExecutionContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

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
