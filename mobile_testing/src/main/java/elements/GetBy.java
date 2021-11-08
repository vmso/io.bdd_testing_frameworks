package elements;

import base.GetFileName;
import configuration.Configuration;
import enums.AppType;
import enums.LocatorTypes;
import exceptions.FileNotFound;
import io.appium.java_client.MobileBy;
import json.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import platform.manager.PlatformManager;
import utils.StoreApiInfo;

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
        var by = StoreApiInfo.get(jsonKey) == null
                ? null : ((HashMap<AppType, By>) StoreApiInfo.get(jsonKey)).get(platformType);
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
            StoreApiInfo.put(jsonKey, byMap);
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
            case LINK_TEXT -> {
                return MobileBy.linkText(value);
            }
            case PREDICATE_STRING -> {
                return MobileBy.iOSNsPredicateString(value);
            }
            case CLASS_NAME -> {
                return MobileBy.className(value);
            }
            default -> throw new IllegalStateException("Unexpected locator value: " + type);
        }
    }

}
