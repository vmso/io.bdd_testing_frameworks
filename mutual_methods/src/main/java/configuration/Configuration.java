package configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;
import java.util.Optional;

import static enums.PropertiesType.*;

public class Configuration {

    private static volatile Configuration instance;
    private final Properties configProps;
    private static final Logger log = LogManager.getLogger(Configuration.class);
    private static final String PROP_FILE_NAME = "config.properties";
    
    // Environment variable prefixes
    private static final String ENV_PREFIX = "TEST_";
    private static final String SECRET_PREFIX = "SECRET_";

    private Configuration() {
        configProps = new Properties();
        loadProperties();
        log.info("Configuration loaded successfully");
    }

    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new Configuration();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try {
            // Load from properties file
            try (InputStream is = ClassLoader.getSystemResourceAsStream(PROP_FILE_NAME)) {
                if (is != null) {
                    configProps.load(is);
                    log.info("Properties file loaded: {}", PROP_FILE_NAME);
                } else {
                    log.warn("Properties file not found: {}", PROP_FILE_NAME);
                }
            }
            
            // Override with environment variables
            overrideWithEnvironmentVariables();
            
        } catch (Exception e) {
            log.error("Error loading configuration", e);
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    private void overrideWithEnvironmentVariables() {
        Properties envProps = System.getProperties();
        envProps.stringPropertyNames().stream()
                .filter(key -> key.startsWith(ENV_PREFIX))
                .forEach(key -> {
                    String configKey = key.substring(ENV_PREFIX.length()).toLowerCase();
                    String value = envProps.getProperty(key);
                    configProps.setProperty(configKey, value);
                    log.debug("Environment variable override: {} = {}", configKey, maskSensitiveValue(configKey, value));
                });
    }

    private String maskSensitiveValue(String key, String value) {
        if (key.contains("password") || key.contains("token") || key.contains("secret")) {
            return value != null ? "***" + value.substring(Math.max(0, value.length() - 4)) : null;
        }
        return value;
    }

    // Secure getters for sensitive data
    public String getSlackToken() {
        return getSecureProperty(SLACK_TOKEN.getText());
    }

    public String getConnectionString() {
        return getStringValueOfProp(CONNECTION_STRING.getText());
    }

    public String getDbClass() {
        return getStringValueOfProp(DB_CLASS.getText());
    }

    public String getDbPassword() {
        return getSecureProperty(DB_PASSWORD.getText());
    }

    public String getDbUser() {
        return getStringValueOfProp(DB_USER.getText());
    }

    public String getWebhook() {
        return getSecureProperty(WEBHOOK.getText());
    }

    private String getSecureProperty(String propKey) {
        String value = getStringValueOfProp(propKey);
        if (value != null) {
            log.debug("Retrieved secure property: {} = {}", propKey, maskSensitiveValue(propKey, value));
        }
        return value;
    }

    public String getStringValueOfProp(String propKey) {
        return configProps.getProperty(propKey);
    }

    public Optional<String> getOptionalStringValueOfProp(String propKey) {
        return Optional.ofNullable(getStringValueOfProp(propKey));
    }

    public Integer getIntegerValueOfProp(String propKey) {
        String value = configProps.getProperty(propKey);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                log.warn("Invalid integer value for property {}: {}", propKey, value);
                return null;
            }
        }
        return null;
    }

    public Optional<Integer> getOptionalIntegerValueOfProp(String propKey) {
        return Optional.ofNullable(getIntegerValueOfProp(propKey));
    }

    public Boolean getBooleanValueOfProp(String propKey) {
        String value = configProps.getProperty(propKey);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return null;
    }

    public Optional<Boolean> getOptionalBooleanValueOfProp(String propKey) {
        return Optional.ofNullable(getBooleanValueOfProp(propKey));
    }

    public String getStringValueOfProp(String propKey, String defaultValue) {
        return configProps.getProperty(propKey, defaultValue);
    }

    public Integer getIntegerValueOfProp(String propKey, Integer defaultValue) {
        Integer value = getIntegerValueOfProp(propKey);
        return value != null ? value : defaultValue;
    }

    public Boolean getBooleanValueOfProp(String propKey, Boolean defaultValue) {
        Boolean value = getBooleanValueOfProp(propKey);
        return value != null ? value : defaultValue;
    }

    // Method to check if running in specific environment
    public boolean isDevelopment() {
        return "development".equalsIgnoreCase(getStringValueOfProp("environment"));
    }

    public boolean isProduction() {
        return "production".equalsIgnoreCase(getStringValueOfProp("environment"));
    }

    public boolean isTest() {
        return "test".equalsIgnoreCase(getStringValueOfProp("environment"));
    }

    // Self-heal convenience getters with defaults
    public boolean isSelfHealEnabled() {
        return Boolean.parseBoolean(getStringValueOfProp("selfheal.enabled", "true"));
    }

    public boolean isSelfHealShadowMode() {
        return Boolean.parseBoolean(getStringValueOfProp("selfheal.shadow_mode", "true"));
    }

    // Method to get all properties (for debugging, excluding sensitive data)
    public Properties getNonSensitiveProperties() {
        Properties safeProps = new Properties();
        configProps.stringPropertyNames().stream()
                .filter(key -> !isSensitiveProperty(key))
                .forEach(key -> safeProps.setProperty(key, configProps.getProperty(key)));
        return safeProps;
    }

    private boolean isSensitiveProperty(String key) {
        String lowerKey = key.toLowerCase();
        return lowerKey.contains("password") || 
               lowerKey.contains("token") || 
               lowerKey.contains("secret") || 
               lowerKey.contains("key");
    }
}