package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;

public class ParseHelper {
    public final Logger log = LogManager.getLogger(ParseHelper.class);


    /**
     * it parses string to integer if the string parsable to integer.
     * it loges the error if the string is not parsable to integer and returns null.
     *
     * @param value is string value to parse to integer.
     * @return is parsed integer value.
     */
    public Integer parsStringToInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            log.warn("Found json value is integer, but the {} isn't parsable to integer, " +
                    "please set  a new integer value for update.", value);
        }
        return null;
    }

    /**
     * it parses string to boolean if the string parsable to boolean.
     * it loges the error if the string is not parsable to boolean and returns null.
     *
     * @param value is string value to parse to boolean.
     * @return is parsed boolean value.
     */
    public Boolean parseStringToBoolean(String value) {
        if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("true")) {
            return Boolean.parseBoolean(value);
        } else {
            log.warn("Found json value is boolean, but the {}, isn't boolean string, please set " +
                    "a new ture or false string for update", value);
        }
        return false;
    }

    /**
     * it parses string to float if the string parsable to float.
     * it loges the error if the string is not parsable to float and returns null.
     *
     * @param value is string value to parse to float.
     * @return is parsed float value.
     */
    public Float parsStringToFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            log.warn("Found json value is float, but the {} isn't parsable to float, " +
                    "please set  a new float value for update.", value);
        }
        return null;
    }

    /**
     * it parses string to double if the string parsable to double.
     * it loges the error if the string is not parsable to double and returns null.
     *
     * @param value is string value to parse to double.
     * @return is parsed double value.
     */
    public Double parsStringToDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            log.warn("Found json value is double, but the {} isn't parsable to double, " +
                    "please set  a new double value for update.", value);
        }
        return null;
    }

    /**
     * it parses string to BigInteger if the string parsable to BigInteger.
     * it loges the error if the string is not parsable to BigInteger and returns null.
     *
     * @param value is string value to parse to integer.
     * @return is parsed integer value.
     */
    public BigInteger parsStringToBigint(String value) {
        try {
            return new BigInteger(value);
        } catch (NumberFormatException e) {
            log.warn("Found json value is BigInteger, but the {} isn't parsable to BigInteger, " +
                    "please set  a new BigInteger value for update.", value);
        }
        return null;
    }

}
