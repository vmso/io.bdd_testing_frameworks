package elements;

import org.openqa.selenium.By;

public class CustomBys {

    public static By byText(String text) {
        return new Text(text);
    }
}
