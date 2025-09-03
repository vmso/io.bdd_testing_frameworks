package elements;

import exceptions.FileNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Refactored GetElement class that uses the new element finding architecture.
 * Now uses ElementFinder interface for better testability and separation of concerns.
 */
public class GetElement extends GetBy {

    private final ElementFinder elementFinder;


    /**
     * Creates a GetElement instance with the default WebDriver-based element finder.
     */
    public GetElement() {
        this(new WebDriverElementFinder());
    }

    /**
     * Creates a GetElement instance with a custom element finder.
     * Useful for testing and dependency injection.
     * 
     * @param elementFinder the element finder to use
     */
    public GetElement(ElementFinder elementFinder) {
        this.elementFinder = elementFinder;
    }

    /**
     * Gets a single element using a JSON key.
     * 
     * @param jsonKey the JSON key for the locator
     * @return the found WebElement
     * @throws FileNotFound if the locator file is not found
     */
    protected WebElement getElement(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        return elementFinder.findElement(by);
    }

    /**
     * Gets a single element using a By locator.
     * 
     * @param by the By locator
     * @return the found WebElement
     */
    protected WebElement getElement(By by) {
        return elementFinder.findElement(by);
    }

    /**
     * Gets multiple elements using a JSON key.
     * 
     * @param jsonKey the JSON key for the locator
     * @return list of found WebElements
     * @throws FileNotFound if the locator file is not found
     */
    protected List<WebElement> getElements(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        return elementFinder.findElements(by);
    }


    /**
     * Gets multiple elements using a By locator.
     * 
     * @param by the By locator
     * @return list of found WebElements
     */
    protected List<WebElement> getElements(By by) {
        return elementFinder.findElements(by);
    }
}
