package elements;

import base.App;
import base.GetFileName;
import configuration.Configuration;
import enums.LocatorTypes;
import exceptions.FileNotFound;
import io.appium.java_client.MobileBy;
import json.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Locale;

public class GetBy {

    private final Logger log = LogManager.getLogger(GetBy.class);

    public By getBy(String jsonKey) throws FileNotFound {
        var jsonMap = getByMap(jsonKey);
        var locatorType = jsonMap.get("locatorType");
        var locatorValue = jsonMap.get("locatorValue");
        var type = LocatorTypes.valueOf(locatorType.toUpperCase(Locale.ENGLISH));

        return getBy(type, locatorValue);
    }

    private HashMap<String, String> getByMap(String jsonKey) throws FileNotFound {
        var filePath = GetFileName.getInstance().getFileName();
        var locatorFolder = Configuration.getInstance().getStringValueOfProp("locator_folder");
        filePath = locatorFolder != null ? "locators/" + filePath + ".json" : filePath + ".json";
        var jsonReader = new JsonReader();
        var jsonMapWithObj = jsonReader.getJsonAsMap(filePath, jsonKey);
        var jsonMap = new HashMap<String, String>();
        jsonMapWithObj.forEach((k, v) -> jsonMap.put(k, String.valueOf(v)));
        return jsonMap;
    }

    private By getBy(LocatorTypes type, String value) {

        switch (type) {
            case ID -> {
                return MobileBy.id(value);
            }
            case ACCESSIBILITY_ID -> {
                return MobileBy.AccessibilityId(value);
            }
            case XPATH -> {
                return MobileBy.xpath(value);
            }
            case NAME -> {
                return MobileBy.name(value);
            }
            case ANDROID_UI_AUTOMATOR -> {
                return MobileBy.AndroidUIAutomator(value);
            }
            case CLASS_CHAIN_IOS -> {
                return MobileBy.iOSClassChain(value);
            }
            case PREDICATE_STRING -> {
                return MobileBy.iOSNsPredicateString(value);
            }
            default -> throw new IllegalStateException("Unexpected locator value: " + type);
        }
    }

}
