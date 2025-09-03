package elements;

import base.GetFileName;
import configuration.Configuration;
import enums.AppType;
import enums.LocatorTypes;
import exceptions.FileNotFound;
import json.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import platform.manager.PlatformManager;
import utils.ReuseStoreData;
import io.appium.java_client.AppiumBy;

import java.util.HashMap;
import java.util.Locale;

public abstract class GetBy {

    private final Logger log = LogManager.getLogger(GetBy.class);

    public By getByValue(String jsonKey) throws FileNotFound {
        var filePath = GetFileName.getInstance().getFileName();
        var platformType = PlatformManager.getInstances().getPlatform();
        var locatorFolder = Configuration.getInstance().getStringValueOfProp("locator_folder");
        filePath = locatorFolder != null ? "locators/" + filePath + ".json" : filePath + ".json";
        @SuppressWarnings("unchecked")
        var by = ReuseStoreData.get(jsonKey) == null
                ? null : ((HashMap<AppType, By>) ReuseStoreData.get(jsonKey)).get(platformType);
        if (by != null) {
            return by;
        } else {
            var jsonMap = getByMap(jsonKey, filePath);
            if (jsonMap.get(platformType.getValue()) == null) {
                log.fatal("""
                        Locator {} type couldn't find in "{}"
                        file with {} json key
                        locator json shuld be contain
                        Android and IOS type of locator"
                        """, platformType, filePath, jsonKey);
                throw new IllegalArgumentException();
            }
            var locatorType = jsonMap.get(platformType.getValue()).get("locatorType");
            var locatorValue = jsonMap.get(platformType.getValue()).get("locatorValue");
            var type = LocatorTypes.valueOf(locatorType.toUpperCase(Locale.ENGLISH));
            by = getByValue(type, locatorValue);
            var byMap = new HashMap<AppType, By>();
            byMap.put(platformType, by);
            ReuseStoreData.put(jsonKey, byMap);
            return getByValue(type, locatorValue);
        }
    }

    private HashMap<String, HashMap<String, String>> getByMap(String jsonKey, String filePath) throws FileNotFound {
        var jsonReader = new JsonReader();
        var jsonMapWithObj = jsonReader.getJsonAsMap(filePath, jsonKey);
        var jsonMap = new HashMap<String, HashMap<String, String>>();
        jsonMapWithObj
                .forEach((k, v) -> jsonMap.put(k, (HashMap<String, String>) v));
        return jsonMap;
    }

    private By getByValue(LocatorTypes type, String value) {

        switch (type) {
            case ID -> {
                return By.id(value);
            }
            case ACCESSIBILITY_ID -> {
                return AppiumBy.accessibilityId(value);
            }
            case XPATH -> {
                return By.xpath(value);
            }
            case NAME -> {
                return By.name(value);
            }
            case ANDROID_UI_AUTOMATOR -> {
                return AppiumBy.androidUIAutomator(value);
            }
            case CLASS_CHAIN_IOS -> {
                return AppiumBy.iOSClassChain(value);
            }
            case LINK_TEXT -> {
                return By.linkText(value);
            }
            case PREDICATE_STRING -> {
                return AppiumBy.iOSNsPredicateString(value);
            }
            case CLASS_NAME -> {
                return By.className(value);
            }

            default -> throw new IllegalStateException("Unexpected locator value: " + type);
        }
    }

}
