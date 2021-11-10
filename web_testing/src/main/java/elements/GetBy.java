package elements;

import base.GetFileName;
import configuration.Configuration;
import enums.LocatorType;
import enums.RelativeType;
import exceptions.FileNotFound;
import json.JsonReader;
import org.openqa.selenium.By;
import utils.StoreApiInfo;

import java.util.Locale;
import java.util.Map;

import static enums.RelativeType.NEAR;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public abstract class GetBy {

    public By getByValue(String jsonKey) throws FileNotFound {
        var byObj = StoreApiInfo.get(jsonKey);
        if (byObj != null) {
            return (By) byObj;
        } else {
            var filePath = GetFileName.getInstance().getFileName();
            var locatorFolder = Configuration.getInstance().getStringValueOfProp("locator_folder");
            filePath = locatorFolder != null ? "locators/" + filePath + ".json" : filePath + ".json";
            var jsonReader = new JsonReader();
            var jsonMap = jsonReader.getJsonAsMap(filePath, jsonKey);
            var isRelative = Boolean.parseBoolean(String.valueOf(jsonMap.get("isRelative")));
            if (isRelative) {
                return getByValue(jsonMap);
            } else {
                var locatorType = LocatorType.valueOf(String.valueOf(jsonMap.get("locatorType")));
                var locatorValue = String.valueOf(jsonMap.get("locatorValue"));
                return getByValue(locatorType, locatorValue);
            }
        }
    }

    private By getByValue(LocatorType type, String value) {

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

    private static By getByValue(RelativeType relativeType, By firstBy, By secondBy, int... atMostDistanceInPixels) {
        switch (relativeType) {
            case ABOVE:
                return with(firstBy).above(secondBy);
            case BELOW:
                return with(firstBy).below(secondBy);
            case NEAR:
                if (atMostDistanceInPixels.length > 0 && atMostDistanceInPixels[0] > 0) {
                    return with(firstBy).near(secondBy, atMostDistanceInPixels[0]);
                } else {
                    return with(firstBy).near(secondBy);
                }
            case LEFT_OF:
                return with(firstBy).toLeftOf(secondBy);
            case RIGHT_OF:
                return with(firstBy).toRightOf(secondBy);
            default:
                throw new IllegalArgumentException(String.format("%s is undefinded relative type", relativeType));
        }
    }

    private By getByValue(Map<String, Object> params) {
        @SuppressWarnings("unchecked")
        var locators = (Map<String,Object>) params.get("locators");
        @SuppressWarnings("unchecked")
        var firstSelector = (Map<String, String>) locators.get("firstLocator");
        @SuppressWarnings("unchecked")
        var secondSelector = (Map<String, String>) locators.get("secondLocator");
        var firstByType = LocatorType.valueOf(String.valueOf(firstSelector.get("locatorType")));
        var firstByValue = String.valueOf(firstSelector.get("locatorValue"));
        var secondByType = LocatorType.valueOf(String.valueOf(secondSelector.get("locatorType")));
        var secondByValue = String.valueOf(secondSelector.get("locatorValue"));

        int atMostDistanceInPixels;
        if (params.get("atMostDistanceInPixels") == null) atMostDistanceInPixels = 0;
        else atMostDistanceInPixels = Integer.parseInt(String.valueOf(params.get("atMostDistanceInPixels")));

        By firstBy = getByValue(firstByType, firstByValue);
        By secondBy = getByValue(secondByType, secondByValue);

        var locatorType = String.valueOf(params.get("relativeType"));
        var relativeType = RelativeType.valueOf(locatorType.toUpperCase(Locale.ENGLISH));
        if (relativeType == NEAR && atMostDistanceInPixels > 0)
            return getByValue(relativeType, firstBy, secondBy, atMostDistanceInPixels);
        else
            return getByValue(relativeType, firstBy, secondBy);
    }

}
