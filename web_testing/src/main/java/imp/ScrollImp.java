package imp;

import com.thoughtworks.gauge.Step;
import helpers.ScrollHelper;
import io.cucumber.java.en.And;

public class ScrollImp extends ScrollHelper {

    @Step("Scroll to top of the page")
    @And("Scroll to top of the page")
    public void scrollTopOfThePage() {
        pageUp();
    }

    @Step("Scroll to bottom of the page")
    @And("Scroll to bottom of the page")
    public void scrollBottomOfThePage() {
        pageDown();
    }

    @Step("Scroll to <element>")
    @And("Scroll to <string> element")
    public void scrollBottomOfThePage(String jsonKey) {
        scrollToElement(jsonKey);
    }

    @Step("Scroll to coordinate x= <x>, y= <y>")
    @And("Scroll to coordinate x= {int}, y= {int}")
    public void scrollBottomOfThePage(int x, int y) {
        scrollToPoint(x, y);
    }
}
