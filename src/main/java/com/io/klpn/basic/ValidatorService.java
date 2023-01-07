package com.io.klpn.basic;

import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import org.springframework.stereotype.Component;

@Component
public class ValidatorService {

    public static final String NAME_REGEX = "[\\p{Alpha}\\p{Space}-.']++";
    public static final String EMAIL_REGEX = "[\\p{Alpha}\\p{Digit}]++@[\\p{Alpha}\\p{Digit}]++.[\\p{Alpha}\\p{Digit}]++";
    public static final String PHONE_NUMBER_REGEX = "[\\d]++";
    public static final String PASSWORD_REGEX = "[\\p{Alnum}\\p{Punct}]++";
    public static final Integer MAX_LENGTH_255 = 255;
    public static final Integer MAX_LENGTH_45 = 45;
    public static final Integer MAX_LENGTH_9 = 9;
    public static final Integer MIN_LENGTH_3 = 3;
    public final static Integer MAX_MINUTE= 60;
    public final static Integer MIN_MINUTE = 0;
    public final static Integer MAX_PITCH_NUMBER = 3;
    public final static Integer MIN_PITCH_NUMBER = 0;

    public void isNull(String fieldName, Object fieldValue) {
        if (fieldValue == null) {
            throw new NullPointerException(String.format("%s cannot be empty!", fieldName));
        }
    }

    public void validateString(String fieldName, String fieldValue, String regex, Integer maxLength) {
        if (!fieldValue.matches(regex)) {
            throw new StringValidatorException(String.format("%s contains illegal characters!", fieldName));
        }
        if (fieldValue.length() > maxLength || fieldValue.length() < MIN_LENGTH_3) {
            throw new StringValidatorException(String.format("%s must be between %d to %d chars length!", fieldName, MIN_LENGTH_3, maxLength));
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            throw new StringValidatorException("Phone number must contain only digits!");
        }

        if (phoneNumber.length() != MAX_LENGTH_9) {
            throw new StringValidatorException("Phone number must be 9 digits length!");
        }
    }

    public void validateIntegerLessThan(Integer integerToValidate, Integer lessThanValue) {
        if (integerToValidate.compareTo(lessThanValue) < 0) {
            throw new IntegerValidatorException(String.format("%d must be bigger than %d", integerToValidate, lessThanValue));
        }
    }

    public void validateIntegerBiggerThan(Integer integerToValidate, Integer biggerThanValue) {
        if (integerToValidate.compareTo(biggerThanValue) > 0) {
            throw new IntegerValidatorException(String.format("%d must be lower than %d", integerToValidate, biggerThanValue));
        }
    }

    public void validateIdsEquality(Long firstId, Long secondId) {
        if (firstId.equals(secondId)) {
            throw new IntegerValidatorException(String.format("%d must be different than %d", firstId, secondId));
        }
    }
}
