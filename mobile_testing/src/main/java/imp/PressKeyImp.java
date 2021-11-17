package imp;

import com.thoughtworks.gauge.Step;
import helper.PressKeysHelper;
import io.cucumber.java.en.And;

public class PressKeyImp extends PressKeysHelper {

    @Step("This <key> is pressed.")
    public void pressKeyImp(String key) {
        presKeys("key");
    }

    @Step("Press back button")
    @And("Press back button")
    public void pressBackButton() {
        pressBack();
    }

}
