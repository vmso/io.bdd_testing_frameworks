package imp;

import com.thoughtworks.gauge.Step;
import elements.GetBy;
import helper.ScrollHelper;
import io.cucumber.java.en.And;
import platform.manager.PlatformManager;


public class ScrollImp extends ScrollHelper {

    @Step("Scroll to <element> element")
    @And("Scroll to {string} element")
    public void scrollToElementImp(String jsonKey){
        scrollToElement(jsonKey);
    }
}
