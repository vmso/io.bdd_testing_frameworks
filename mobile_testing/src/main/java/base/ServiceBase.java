package base;

import configuration.Configuration;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import utils.StoreApiInfo;

public class ServiceBase {
    private static ServiceBase instance;
    private final AppiumServiceBuilder builder;
    private String ip;
    private Integer port;

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
        if (ip != null) {
            builder.withIPAddress(ip);
            builder.usingPort(port);
        } else
            builder.usingAnyFreePort();
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                .withArgument(GeneralServerFlag.RELAXED_SECURITY);
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        StoreApiInfo.put("service", service);
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
