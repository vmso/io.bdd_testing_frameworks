package elements;

import base.GetFileName;
import exceptions.KeywordNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

class GetByTest extends GetBy {


    @BeforeAll
    public static void setFile() {
        GetFileName.getInstance().setFileName("examples");
    }

    @Test
    void relativeNearTest() {
        var by = getByValue("login_btn_near");
        Assertions.assertEquals(RelativeLocator.RelativeBy.class.getSimpleName(), by.getClass().getSimpleName());
    }

    @Test
    void relativeNearWithoutDistanceTest() {
        var by = getByValue("login_btn_near_without_distance");
        Assertions.assertEquals(RelativeLocator.RelativeBy.class.getSimpleName(), by.getClass().getSimpleName());
    }

    @Test
    void relativeBelowTest() {
        var by = getByValue("login_btn_below");
        Assertions.assertEquals(RelativeLocator.RelativeBy.class.getSimpleName(), by.getClass().getSimpleName());
    }

    @Test
    void relativeBy() {
        var by = getByValue("normal_Locator");
        Assertions.assertEquals(By.ById.class.getSimpleName(), by.getClass().getSimpleName());
    }

    @Test
    void relativeByWithOutLocatorType() {
        Assertions.assertThrows(KeywordNotFound.class, () -> getByValue("normal_Locator_with_out_locator_Type"));
    }
}