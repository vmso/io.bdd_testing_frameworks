package imp;

import com.thoughtworks.gauge.Step;
import helper.RotateScreenHelper;
import io.cucumber.java.en_scouse.An;

public class RotateScreenImp extends RotateScreenHelper {

    @Step("Rotate screen as landscape.")
    @An("Rotate screen as landscape.")
    public void rotateLandscape() {
        rotateScreen("LANDSCAPE");
    }

    @Step("Rotate the screen as  portrait.")
    @An("Rotate the screen as  portrait.")
    public void rotatePortrait() {
        rotateScreen("PORTRAIT");
    }
}
