package elements;

import base.GetFileName;
import configuration.Configuration;
import enums.LocatorType;
import enums.RelativeType;
import exceptions.FileNotFound;
import exceptions.KeywordNotFound;

import json.UIProjectJsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ReuseStoreData;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static enums.RelativeType.NEAR;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class GetBy {

    private String jsonKey;

    protected long DEFAULT_WAIT = 15;
    protected long DEFAULT_SLEEP_IN_MILLIS = 500;
    private static final Logger log = LogManager.getLogger(GetBy.class);

    public By getByValue(String jsonKey) throws FileNotFound {
        this.jsonKey = jsonKey;
        var byObj = ReuseStoreData.get(jsonKey);
        if (byObj != null) {
            return (By) byObj;
        } else {
            var filePath = GetFileName.getInstance().getFileName();
            var locatorFolder = Configuration.getInstance().getStringValueOfProp("locator_folder");
            filePath = locatorFolder != null ? "locators/" + filePath + ".json" : filePath + ".json";
            var jsonReader = new UIProjectJsonReader();
            var jsonMap = jsonReader.getJsonAsMapStringObject(filePath, jsonKey);
            var by = getBy(jsonMap);
            ReuseStoreData.put(jsonKey, by);
            return getBy(jsonMap);
        }
    }

    private By getByValueAccordingType(LocatorType type, String value) {

        switch (type) {
            case ID -> {
                return By.id(value);
            }
            case CSS_SELECTOR -> {
                return By.cssSelector(value);
            }
            case XPATH -> {
                return By.xpath(value);
            }
            case NAME -> {
                return By.name(value);
            }
            case CLASS_NAME -> {
                return By.className(value);
            }
            case PARTIAL_LINK_TEXT -> {
                return By.partialLinkText(value);
            }
            case LINK_TEXT -> {
                return By.linkText(value);
            }
            case TAG_NAME -> {
                return By.tagName(value);
            }
            default -> throw new IllegalArgumentException(String.format("%s is undefined parameter type", type));
        }
    }

    private By getRelativeByAccordingType(RelativeType relativeType, By firstBy, By secondBy, int... atMostDistanceInPixels) {
        WebElement firstElement;
        try {
            firstElement = new GetElement().getElement(firstBy);
        } catch (Exception e) {
            log.fatal("{}'s first element couldn't find on the page, first element by value '{}'", jsonKey, firstBy);
            throw e;
        }

        switch (relativeType) {
            case ABOVE:
                return with(secondBy).above(firstElement);
            case BELOW:
                return with(secondBy).below(firstElement);
            case NEAR:
                if (atMostDistanceInPixels.length > 0 && atMostDistanceInPixels[0] > 0) {
                    return with(secondBy).near(firstElement, atMostDistanceInPixels[0]);
                } else {
                    return with(secondBy).near(firstElement);
                }
            case LEFT_OF:
                return with(secondBy).toLeftOf(firstElement);
            case RIGHT_OF:
                return with(secondBy).toRightOf(firstElement);
            default:
                throw new IllegalArgumentException(String.format("%s is undefined relative type", relativeType));
        }
    }

    private By getRelativeByValue(Map<String, Object> params) {
        var locators = getRelativeLocatorsFromMap(params);
        var firstLocator = getLocatorWithKey(locators, "firstLocator");
        var firstByType = getLocatorType(firstLocator);
        var firstByValue = getLocatorValue(firstLocator);
        var secondLocator = getLocatorWithKey(locators, "secondLocator");
        var secondByType = getLocatorType(secondLocator);
        var secondByValue = getLocatorValue(secondLocator);
        int atMostDistanceInPixels = getAtMostDistanceInPixels(params);
        By firstBy = getByValueAccordingType(firstByType, firstByValue);
        By secondBy = getByValueAccordingType(secondByType, secondByValue);

        var locatorType = String.valueOf(params.get("relativeType"));
        var relativeType = RelativeType.valueOf(locatorType.toUpperCase(Locale.ENGLISH));
        if (relativeType == NEAR && atMostDistanceInPixels > 0)
            return getRelativeByAccordingType(relativeType, firstBy, secondBy, atMostDistanceInPixels);
        else
            return getRelativeByAccordingType(relativeType, firstBy, secondBy);
    }

    private Map<String, Object> getRelativeLocatorsFromMap(Map<String, Object> params) {
        var locators = params.get("locators");
        if (locators != null) {
            @SuppressWarnings("unchecked")
            var locatorsMap = (Map<String, Object>) locators;
            return locatorsMap;
        } else
            throw new KeywordNotFound("locators", this.jsonKey);
    }

    private Map<String, String> getLocatorWithKey(Map<String, Object> params, String locatorKey) {
        var locator = params.get(locatorKey);
        if (locator != null) {
            @SuppressWarnings("unchecked")
            var locatorMap = (Map<String, String>) locator;
            return locatorMap;
        } else
            throw new KeywordNotFound(locatorKey, this.jsonKey);
    }

    private LocatorType getLocatorType(Map<String, String> locator) {
        var locatorType = locator.get("locatorType");
        if (locatorType != null)
            return LocatorType.valueOf(locatorType);
        else
            throw new KeywordNotFound("locatorType", this.jsonKey);
    }

    private String getLocatorValue(Map<String, String> locator) {
        var locatorValue = locator.get("locatorValue");
        if (locatorValue != null)
            return locatorValue;
        else
            throw new KeywordNotFound("locatorValue", this.jsonKey);
    }

    private Integer getAtMostDistanceInPixels(Map<String, Object> params) {
        int atMostDistanceInPixels;
        if (params.get("atMostDistanceInPixels") == null) atMostDistanceInPixels = 0;
        else atMostDistanceInPixels = Integer.parseInt(String.valueOf(params.get("atMostDistanceInPixels")));
        return atMostDistanceInPixels;
    }

    private By getBy(Map<String, Object> jsonMap) {
        var isRelative = Boolean.parseBoolean(String.valueOf(jsonMap.get("isRelative")));
        if (isRelative) {
            return getRelativeByValue(jsonMap);
        } else {
            Map<String, String> locatorMap = new HashMap<>();
            jsonMap.forEach((k, v) -> locatorMap.put(k, String.valueOf(v)));
            var locatorType = getLocatorType(locatorMap);
            var locatorValue = getLocatorValue(locatorMap);
            return getByValueAccordingType(locatorType, locatorValue);
        }
    }
}
