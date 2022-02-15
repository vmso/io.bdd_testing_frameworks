package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import helper.RandomHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RandomDataStore extends RandomHelper {

    private final Logger log = LogManager.getLogger(RandomDataStore.class);
    private final static String PHONE_INFO = "Phone number created as:{}";
    private final static String MAIL_INFO = "Mail address created as:{}";
    private final static String SPECIFY_DIGIT_NUMBER = "The {} digit number created, number is {}";
    private final static String BETWEEN_NUMBER = "The digit number created between {} and {}, number is {}";


    @Step("Generate new phone number and store as <key> during scenario")
    public void generatePhoneNumberScenario(String key) {
        ScenarioDataStore.put(key, generateGsmNumber());
        log.info(PHONE_INFO, ScenarioDataStore.get(key));
    }

    @Step("Generate new phone number and store as <key> during suit")
    public void generatePhoneNumberSuit(String key) {
        SuiteDataStore.put(key, generateGsmNumber());
        log.info(PHONE_INFO, SuiteDataStore.get(key));

    }

    @Step("Generate new phone number and store as <key> during spec")
    public void generatePhoneNumberSpec(String key) {
        SpecDataStore.put(key, generateGsmNumber());
        log.info(PHONE_INFO, SpecDataStore.get(key));
    }

    @Step("Generate new mail address and store as <key> during scenario")
    public void generateMailScenario(String key) {
        ScenarioDataStore.put(key, generateMail());
        log.info(MAIL_INFO, ScenarioDataStore.get(key));

    }

    @Step("Generate new mail and store as <key> during suit")
    public void generateMailSuit(String key) {
        SuiteDataStore.put(key, generateMail());
        log.info(MAIL_INFO, SuiteDataStore.get(key));

    }

    @Step("Generate new phone number and store as <key> during spec")
    public void generateMailSpec(String key) {
        SpecDataStore.put(key, generateMail());
        log.info(MAIL_INFO, SpecDataStore.get(key));

    }

    @Step({"Generate a <digit> digit number and store it in Scenario store with <> key as string type",
            "Sayı oluştur, <basamakl> basamaklı ve senaryo deposuna <anahtar> anahtarı ile dizi olarak kaydet"})
    public void generateSpecificDigitNumberStoreScenario(int digit, String key) {
        ScenarioDataStore.put(key, generateNumberByNumberOfDigitAsString(digit));
        log.info(SPECIFY_DIGIT_NUMBER, digit, ScenarioDataStore.get(key));

    }

    @Step({"Generate a <digit> digit number and store it in Spec store with <> key as string type",
            "Sayı oluştur, <basamakl> basamaklı ve spec deposuna <anahtar> anahtarı ile dizi olarak kaydet"})
    public void generateSpecificDigitNumberStoreSpec(int digit, String key) {
        SpecDataStore.put(key, generateNumberByNumberOfDigitAsString(digit));
        log.info(SPECIFY_DIGIT_NUMBER, digit, SpecDataStore.get(key));
    }

    @Step({"Generate a <digit> digit number and store it in Suit store with <> key as string type",
            "Sayı oluştur, <basamakl> basamaklı ve suit deposuna <anahtar> anahtarı ile dizi olarak kaydet"})
    public void generateSpecificDigitNumberStoreSuit(int digit, String key) {
        SuiteDataStore.put(key, generateNumberByNumberOfDigitAsString(digit));
        log.info(SPECIFY_DIGIT_NUMBER, digit, SuiteDataStore.get(key));
    }

    @Step({"Generate a max <max> digit number and store it in Suit store as <> key",
            "Sayı oluştur,en fazla <basamakl> basamaklı ve suit deposuna <anahtar> anahtarı ile kaydet"})
    public void generateNumberStoreSuit(int digit, String key) {
        SpecDataStore.put(key, generateNumber(digit));
        log.info(SPECIFY_DIGIT_NUMBER, String.valueOf(SuiteDataStore.get(key)).length(),
                SuiteDataStore.get(key));
    }

    @Step({"Generate a max <max> digit number and store it in Spec store as <> key",
            "Sayı oluştur,en fazla <basamakl> basamaklı ve spec deposuna <anahtar> anahtarı ile kaydet"})
    public void generateNumberStoreSpec(int digit, String key) {
        SpecDataStore.put(key, generateNumber(digit));
        log.info(SPECIFY_DIGIT_NUMBER, String.valueOf(SpecDataStore.get(key)).length(),
                SpecDataStore.get(key));
    }

    @Step({"Generate a max <max> digit number and store it in Scenario store as <> key",
            "Sayı oluştur,en fazla <basamakl> basamaklı ve senaryo deposuna <anahtar> anahtarı ile kaydet"})
    public void generateNumberStoreScenario(int digit, String key) {
        ScenarioDataStore.put(key, generateNumber(digit));
        log.info(SPECIFY_DIGIT_NUMBER, String.valueOf(ScenarioDataStore.get(key)).length(),
                ScenarioDataStore.get(key));
    }

    @Step({"Generate a number between <begin number> and <end number> and end and store it in Spec store with <> key",
            "Sayı oluştur, <ilk numara> ile <son numara> arasında ve spec deposuna <anahtar> anahtarı ile kaydet"})
    public void generateBetweenNumberStoreSpec(int beginNumber, int endNumber, String key) {
        SpecDataStore.put(key, generateNumberBetweenTwoBound(beginNumber, endNumber));
        log.info(BETWEEN_NUMBER, beginNumber, endNumber, SpecDataStore.get(key));
    }

    @Step({"Generate a number between <begin number> and <end number> and end and store it in Suit store with <> key",
            "Sayı oluştur, <ilk numara> ile <son numara> arasında ve suit deposuna <anahtar> anahtarı ile kaydet"})
    public void generateBetweenNumberStoreSuit(int beginNumber, int endNumber, String key) {
        SuiteDataStore.put(key, generateNumberBetweenTwoBound(beginNumber, endNumber));
        log.info(BETWEEN_NUMBER, beginNumber, endNumber, SuiteDataStore.get(key));
    }

    @Step({"Generate a number between <begin number> and <end number> and end and store it in Scenario store with <> key",
            "Sayı oluştur, <ilk numara> ile <son numara> arasında ve senaryo deposuna <anahtar> anahtarı ile kaydet"})
    public void generateBetweenNumberStoreScenario(int beginNumber, int endNumber, String key) {
        ScenarioDataStore.put(key, generateNumberBetweenTwoBound(beginNumber, endNumber));
        log.info(BETWEEN_NUMBER, beginNumber, endNumber, ScenarioDataStore.get(key));
    }
}
