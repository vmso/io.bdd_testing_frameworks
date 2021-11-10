package elements;

import base.GetFileName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.locators.RelativeLocator;

class GetByTest extends GetBy {


    @Test
    void relativeTest() throws Exception {
        GetFileName.getInstance().setFileName("examples");
        var by = getByValue("login_btn_near_2");
        Assertions.assertEquals(RelativeLocator.RelativeBy.class.getSimpleName(), by.getClass().getSimpleName());
    }
}