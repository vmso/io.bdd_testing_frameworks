package helper;

import enums.GsmType;
import enums.MailType;

import java.security.SecureRandom;
import java.util.Random;

import static enums.MailType.HOTMAIL;

public class RandomHelper {


    protected String generateMail() {
        return generateMail(generateNumberBetweenTwoBound(4, 10));
    }

    protected String generateMail(int numberOfDigit, MailType mailType) {
        return generateAlphabetic(numberOfDigit).toLowerCase() + mailType.extension;
    }

    protected String generateMail(int numberOfDigit) {
        return generateMail(numberOfDigit, HOTMAIL);
    }

    protected String generateNumberByNumberOfDigitAsString(int numberOfDigit) {
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < numberOfDigit; i++) {
            int generatedNumber = generateNumber(10);

            if (i == 0 && generatedNumber == 0)
                ++generatedNumber;
            randomNumber.append(generatedNumber);
        }
        return randomNumber.toString();
    }


    protected int generateNumber(int bound) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(bound);
    }


    protected String generateAlphanumeric(int numberOfDigit) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return createRandomString(chars, numberOfDigit);
    }

    protected String generateAlphabetic(int numberOfDigit) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return createRandomString(chars, numberOfDigit);
    }

    private String createRandomString(String source, int numberOfDigit) {
        Random rnd = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(numberOfDigit);
        for (int i = 0; i < numberOfDigit; i++)
            stringBuilder.append(source.charAt(rnd.nextInt(source.length())));
        return stringBuilder.toString();
    }

    protected int generateNumberBetweenTwoBound(int beginBound, int endBound) {
        if (beginBound > endBound) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        } else {
            return generateNumber((endBound - beginBound) + 1) + beginBound;
        }
    }

    protected String generateGsmNumber() {
        return generateGsmNumber(GsmType.TURK_CELL);
    }

    /**
     * Rastgele telefon numarası üretir
     *
     * @param gsmType Gsm operatörü ne olsun? Örn: Vodafone, Turkcell vb.
     * @return String
     */
    protected String generateGsmNumber(GsmType gsmType) {
        int areCodeListSize = gsmType.getAreCodeList().size();
        String areaCode = gsmType.getAreCodeList().get(generateNumber(areCodeListSize));
        String bodyCode = generateNumberByNumberOfDigitAsString(7);
        return areaCode + bodyCode;
    }

    public String hexCodeOfRandomness(int digit) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digit; i++) {
            sb.append(hexDigits[new SecureRandom().nextInt(hexDigits.length)]);
        }
        return sb.toString();
    }

}
