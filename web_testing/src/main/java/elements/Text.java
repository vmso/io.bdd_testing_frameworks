package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Text extends By {

    private final String value;

    public Text(String text){
        this.value =text;
    }

    @Override
    public List<WebElement> findElements(SearchContext searchContext) {
        return searchContext.findElements(By.xpath(String.format("//*[text()='%s']",value)));
    }
}
