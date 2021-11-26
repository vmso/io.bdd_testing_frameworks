package base;

import configuration.Configuration;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import utils.StoreApiInfo;

import java.util.HashMap;

public class ServiceBase {
    private String ip;
    private Integer port;
    private AppiumServiceBuilder builder;
    private static ServiceBase instance;

    private ServiceBase() {
        ip = Configuration.getInstance().getStringValueOfProp("driver_ip");
        port = Configuration.getInstance().getIntegerValueOfProp("port");
        builder = new AppiumServiceBuilder();
    }

    public static ServiceBase getInstances() {
        if (instance == null)
            instance = new ServiceBase();
        return instance;
    }

    public AppiumDriverLocalService getService() {

        return (AppiumDriverLocalService) StoreApiInfo.get("service");
    }

    public void startService() {
        if (getService() != null && getService().isRunning()) {
            stopTheServices();
        }
        setService(ip, null);
        getService().start();
    }

    private void setService(String ip, Integer port) {
        builder.withIPAddress(ip);
        if (port != null)
            builder.usingPort(port);
        else
            builder.usingAnyFreePort();
        builder.withEnvironment(getEnvironment())
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        StoreApiInfo.put("service", service);
    }


    private HashMap<String, String> getEnvironment() {
        var env = new HashMap<String, String>();
        var androidHome = Configuration.getInstance().getStringValueOfProp("android_home");
        var java_home = Configuration.getInstance().getStringValueOfProp("java_home");
        env.put("ANDROID_HOME", androidHome);
        env.put("JAVA_HOME", java_home);
        return env;
    }

    public void stopTheServices() {
        if (getService() == null) {
            ip = Configuration.getInstance().getStringValueOfProp("grid_ip");
            port = Configuration.getInstance().getIntegerValueOfProp("grid_port");
            setService(ip, port);
        }
        getService().stop();
    }
}
