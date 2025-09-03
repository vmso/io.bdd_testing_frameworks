package elements.strategies;

import elements.LocatorStrategy;
import elements.Text;
import enums.LocatorType;
import org.openqa.selenium.By;

/**
 * Standard locator strategy implementation for common Selenium locators.
 */
public class StandardLocatorStrategy implements LocatorStrategy {
    
    private final LocatorType locatorType;
    
    public StandardLocatorStrategy(LocatorType locatorType) {
        this.locatorType = locatorType;
    }
    
    @Override
    public By createLocator(String value) {
        return switch (locatorType) {
            case ID -> By.id(value);
            case CSS_SELECTOR -> By.cssSelector(value);
            case XPATH -> By.xpath(value);
            case NAME -> By.name(value);
            case CLASS_NAME -> By.className(value);
            case PARTIAL_LINK_TEXT -> By.partialLinkText(value);
            case LINK_TEXT -> By.linkText(value);
            case TAG_NAME -> By.tagName(value);
            case TEXT -> new Text(value);
            default -> throw new IllegalArgumentException(String.format("%s is undefined parameter type", locatorType));
        };
    }
    
    @Override
    public String getStrategyName() {
        return locatorType.name();
    }
} 