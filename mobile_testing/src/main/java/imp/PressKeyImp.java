package imp;

import com.thoughtworks.gauge.Step;
import helper.PressKeysHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en_scouse.An;

public class PressKeyImp extends PressKeysHelper {

    @Step("Press <key>")
    @An("Press {string}")
    public void pressKeyImp(String key) {
        presKeys(key);
    }

    @Step("Press back button")
    @And("Press back button")
    public void pressBackButton() {
        pressBack();
    }

}
