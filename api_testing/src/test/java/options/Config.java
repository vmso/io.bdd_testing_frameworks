package options;

import java.util.Objects;

public class Config {
    private static Config instance;
    private Config(){

    }
    public static Config getInstance(){
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    public String getReportConfigPath() {
        String reportConfigPath = Objects.requireNonNull(
                getClass().getClassLoader().getResource("extent-config.xml")).getPath();
        if (reportConfigPath != null) return reportConfigPath;
        else
            throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }
}
