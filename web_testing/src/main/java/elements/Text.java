package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Custom locator strategy for finding elements by their exact text content.
 * Extends Selenium's By class to provide text-based element finding capabilities.
 * 
 * Usage: new Text("Submit") or CustomBys.byText("Submit")
 */
public class Text extends By {

    private final String value;

    /**
     * Creates a new Text locator with the specified text value.
     * 
     * @param text the exact text to search for in elements
     */
    public Text(String text) {
        this.value = text;
    }

    /**
     * Finds elements that have the exact text content matching the specified value.
     * Uses XPath to search for elements with matching text.
     * 
     * @param searchContext the context to search within (usually WebDriver or WebElement)
     * @return list of WebElements with matching text content
     */
    @Override
    public List<WebElement> findElements(SearchContext searchContext) {
        return searchContext.findElements(By.xpath(String.format("//*[text()='%s']", value)));
    }
}
