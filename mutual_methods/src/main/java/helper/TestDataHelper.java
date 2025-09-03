package helper;

import net.datafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Modern test data management using Faker library
 * Provides realistic test data for various scenarios
 */
public class TestDataHelper {
    
    private static final Logger log = LogManager.getLogger(TestDataHelper.class);
    private static volatile TestDataHelper instance;
    private final Faker faker;
    private final Map<String, Object> testDataStore;
    
    private TestDataHelper() {
        this.faker = new Faker();
        this.testDataStore = new ConcurrentHashMap<>();
        log.info("TestDataHelper initialized");
    }
    
    public static TestDataHelper getInstance() {
        if (instance == null) {
            synchronized (TestDataHelper.class) {
                if (instance == null) {
                    instance = new TestDataHelper();
                }
            }
        }
        return instance;
    }
    
    // User Data
    public String getRandomFirstName() {
        return faker.name().firstName();
    }
    
    public String getRandomLastName() {
        return faker.name().lastName();
    }
    
    public String getRandomFullName() {
        return faker.name().fullName();
    }
    
    public String getRandomEmail() {
        return faker.internet().emailAddress();
    }
    
    public String getRandomPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    
    public String getRandomUsername() {
        return faker.name().username();
    }
    
    public String getRandomPassword() {
        return faker.internet().password(8, 16, true, true, true);
    }
    
    // Address Data
    public String getRandomStreetAddress() {
        return faker.address().streetAddress();
    }
    
    public String getRandomCity() {
        return faker.address().city();
    }
    
    public String getRandomState() {
        return faker.address().state();
    }
    
    public String getRandomZipCode() {
        return faker.address().zipCode();
    }
    
    public String getRandomCountry() {
        return faker.address().country();
    }
    
    // Company Data
    public String getRandomCompanyName() {
        return faker.company().name();
    }
    
    public String getRandomJobTitle() {
        return faker.job().title();
    }
    
    public String getRandomDepartment() {
        return faker.job().field();
    }
    
    // Financial Data
    public String getRandomCreditCardNumber() {
        return faker.finance().creditCard();
    }
    
    public String getRandomBankAccountNumber() {
        return faker.finance().iban();
    }
    
    public String getRandomCurrency() {
        return faker.currency().code();
    }
    
    public Double getRandomAmount() {
        return faker.number().randomDouble(2, 1, 10000);
    }
    
    // Product Data
    public String getRandomProductName() {
        return faker.commerce().productName();
    }
    
    public String getRandomProductCategory() {
        return faker.commerce().department();
    }
    
    public String getRandomBrand() {
        return faker.commerce().brand();
    }
    
    public Double getRandomPrice() {
        return faker.number().randomDouble(2, 1, 1000);
    }
    
    // Date and Time
    public String getRandomDate() {
        return faker.date().birthday().toString();
    }
    
    public String getRandomFutureDate() {
        // Faker 1.9.0+ requires arguments: days, time unit, reference date
        return faker.date().future(10, java.util.concurrent.TimeUnit.DAYS, new java.util.Date()).toString();
    }
    
    public String getRandomPastDate() {
        return faker.date().past(10, java.util.concurrent.TimeUnit.DAYS, new java.util.Date()).toString();
    }
    
    // Text and Content
    public String getRandomText(int minLength, int maxLength) {
        // Generate a paragraph and trim to the desired length
        String paragraph = faker.lorem().paragraph();
        if (paragraph.length() < minLength) {
            paragraph += " " + faker.lorem().paragraph();
        }
        return paragraph.substring(0, Math.min(paragraph.length(), maxLength));
    }
    
    public String getRandomSentence() {
        return faker.lorem().sentence();
    }
    
    public String getRandomWord() {
        return faker.lorem().word();
    }
    
    // Internet and Technology
    public String getRandomUrl() {
        return faker.internet().url();
    }
    
    public String getRandomIpAddress() {
        return faker.internet().ipV4Address();
    }
    
    public String getRandomUserAgent() {
        return faker.internet().userAgent();
    }
    
    // File and Media
    public String getRandomFileName() {
        return faker.file().fileName();
    }
    
    public String getRandomFileExtension() {
        return faker.file().extension();
    }
    
    public String getRandomMimeType() {
        return faker.file().mimeType();
    }
    
    // Custom Data Generation
    public Map<String, Object> generateUserProfile() {
        Map<String, Object> profile = new HashMap<>();
        profile.put("firstName", getRandomFirstName());
        profile.put("lastName", getRandomLastName());
        profile.put("email", getRandomEmail());
        profile.put("phone", getRandomPhoneNumber());
        profile.put("address", getRandomStreetAddress());
        profile.put("city", getRandomCity());
        profile.put("state", getRandomState());
        profile.put("zipCode", getRandomZipCode());
        profile.put("country", getRandomCountry());
        return profile;
    }
    
    public Map<String, Object> generateProductData() {
        Map<String, Object> product = new HashMap<>();
        product.put("name", getRandomProductName());
        product.put("category", getRandomProductCategory());
        product.put("brand", getRandomBrand());
        product.put("price", getRandomPrice());
        product.put("description", getRandomText(10, 50));
        return product;
    }
    
    public Map<String, Object> generateCompanyData() {
        Map<String, Object> company = new HashMap<>();
        company.put("name", getRandomCompanyName());
        company.put("industry", getRandomDepartment());
        company.put("website", getRandomUrl());
        company.put("phone", getRandomPhoneNumber());
        company.put("address", getRandomStreetAddress());
        return company;
    }
    
    // Test Data Storage
    public void storeTestData(String key, Object value) {
        testDataStore.put(key, value);
        log.debug("Stored test data: {} = {}", key, value);
    }
    
    public Object getTestData(String key) {
        Object value = testDataStore.get(key);
        log.debug("Retrieved test data: {} = {}", key, value);
        return value;
    }
    
    public String getTestDataAsString(String key) {
        Object value = getTestData(key);
        return value != null ? value.toString() : null;
    }
    
    public Integer getTestDataAsInteger(String key) {
        Object value = getTestData(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return value != null ? Integer.parseInt(value.toString()) : null;
    }
    
    public void clearTestData() {
        testDataStore.clear();
        log.info("Test data store cleared");
    }
    
    public void removeTestData(String key) {
        testDataStore.remove(key);
        log.debug("Removed test data: {}", key);
    }
    
    public boolean hasTestData(String key) {
        return testDataStore.containsKey(key);
    }
    
    public Map<String, Object> getAllTestData() {
        return new HashMap<>(testDataStore);
    }
    
    // Utility methods
    public String generateUniqueId() {
        return faker.idNumber().valid();
    }
    
    public String generateRandomString(int length) {
        return faker.regexify("[A-Za-z0-9]{" + length + "}");
    }
    
    public String generateRandomNumericString(int length) {
        return faker.regexify("[0-9]{" + length + "}");
    }
    
    public String generateRandomAlphabeticString(int length) {
        return faker.regexify("[A-Za-z]{" + length + "}");
    }
} 