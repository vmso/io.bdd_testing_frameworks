package helper;

import org.openqa.selenium.remote.RemoteKeyboard;
import platform.manager.PlatformManager;

import java.util.HashMap;

public class GetKeyboard {

    public RemoteKeyboard getKeyBoard() {
        return (RemoteKeyboard) PlatformManager.getInstances().getDriver().getKeyboard();
    }

}
