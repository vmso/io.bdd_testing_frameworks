package elements.strategies;

import elements.LocatorStrategy;
import enums.RelativeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static enums.RelativeType.NEAR;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

/**
 * Strategy for creating relative locators.
 * Handles complex relative positioning logic.
 */
public class RelativeLocatorStrategy implements LocatorStrategy {
    
    private static final Logger log = LogManager.getLogger(RelativeLocatorStrategy.class);
    
    private final Map<String, Object> relativeParams;
    private final String jsonKey;
    
    public RelativeLocatorStrategy(Map<String, Object> relativeParams, String jsonKey) {
        this.relativeParams = relativeParams;
        this.jsonKey = jsonKey;
    }
    
    @Override
    public By createLocator(String value) {
        // This method is not used for relative locators
        // The actual creation is done in createRelativeLocator()
        throw new UnsupportedOperationException("Use createRelativeLocator() for relative locators");
    }
    
    /**
     * Creates a relative locator based on the configured parameters.
     * 
     * @param firstElement the first element to position relative to
     * @param secondBy the locator for the second element
     * @return relative By locator
     */
    public By createRelativeLocator(WebElement firstElement, By secondBy) {
        var relativeType = getRelativeType();
        
        return switch (relativeType) {
            case ABOVE -> with(secondBy).above(firstElement);
            case BELOW -> with(secondBy).below(firstElement);
            case NEAR -> {
                int distance = getAtMostDistanceInPixels();
                if (distance > 0) {
                    yield with(secondBy).near(firstElement, distance);
                } else {
                    yield with(secondBy).near(firstElement);
                }
            }
            case LEFT_OF -> with(secondBy).toLeftOf(firstElement);
            case RIGHT_OF -> with(secondBy).toRightOf(firstElement);
            default -> throw new IllegalArgumentException(String.format("%s is undefined relative type", relativeType));
        };
    }
    
    @Override
    public String getStrategyName() {
        return "RELATIVE";
    }
    
    private RelativeType getRelativeType() {
        var locatorType = String.valueOf(relativeParams.get("relativeType"));
        return RelativeType.valueOf(locatorType.toUpperCase());
    }
    
    private int getAtMostDistanceInPixels() {
        if (relativeParams.get("atMostDistanceInPixels") == null) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(relativeParams.get("atMostDistanceInPixels")));
    }
    
    public Map<String, Object> getRelativeParams() {
        return relativeParams;
    }
    
    public String getJsonKey() {
        return jsonKey;
    }
} 