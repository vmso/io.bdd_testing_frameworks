package imp;

import com.thoughtworks.gauge.Step;
import helper.RotateScreenHelper;
import io.cucumber.java.en.And;

public class RotateScreenImp extends RotateScreenHelper {

    @Step("Rotate screen as landscape.")
    @And("Rotate screen as landscape.")
    public void rotateLandscape() {
        rotateScreen(); // No argument, just call the helper
    }

    @Step("Rotate the screen as portrait.")
    @And("Rotate the screen as portrait.")
    public void rotatePortrait() {
        rotateScreen(); // No argument, just call the helper
    }
}
