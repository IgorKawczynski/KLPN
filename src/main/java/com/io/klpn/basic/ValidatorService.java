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
    public static final Integer MAX_MINUTE = 60;
    public static final Integer MIN_MINUTE = 0;
    public static final Integer MAX_PITCH_NUMBER = 3;
    public static final Integer MIN_PITCH_NUMBER = 0;
    public static final Integer MAX_INDEX_VALUE = 999999;
    public static final Integer MIN_INDEX_VALUE = 100000;
    public static final Integer MIN_TSHIRT_NUMBER = 1;
    public static final Integer MAX_TSHIRT_NUMBER = 99;

    public void isNull(String fieldName, Object fieldValue) {
        if (fieldValue == null) {
            throw new NullPointerException(String.format("Pole %s nie może być puste!", fieldName));
        }
    }

    public void validateString(String fieldName, String fieldValue, String regex, Integer maxLength) {
        if (!fieldValue.matches(regex)) {
            throw new StringValidatorException(String.format("%s zawiera niedozwolone znaki!", fieldName));
        }
        if (fieldValue.length() > maxLength || fieldValue.length() < MIN_LENGTH_3) {
            throw new StringValidatorException(String.format("Długość znaków %s musi się zawierać pomiędzy %d i %d!", fieldName, MIN_LENGTH_3, maxLength));
        }
    }

    public void emailContainsAtSign(String email) {
        if(email != null && !email.contains("@")) {
            throw new StringValidatorException("Email musi zawierać '@' !");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            throw new StringValidatorException("Numer telefonu może zawierać tylko cyfry!");
        }

        if (phoneNumber.length() != MAX_LENGTH_9) {
            throw new StringValidatorException("Numer telefonu musi mieć 9 cyfr!");
        }
    }

    public void validateIntegerLessThan(Integer integerToValidate, Integer lessThanValue) {
        if (integerToValidate.compareTo(lessThanValue) < 0) {
            throw new IntegerValidatorException(String.format("%d musi być większe niż %d", integerToValidate, lessThanValue));
        }
    }

    public void validateIntegerBiggerThan(Integer integerToValidate, Integer biggerThanValue) {
        if (integerToValidate.compareTo(biggerThanValue) > 0) {
            throw new IntegerValidatorException(String.format("%d musi być mniejsze niż %d", integerToValidate, biggerThanValue));
        }
    }

    public void validateIdsEquality(Long firstId, Long secondId) {
        if (firstId.equals(secondId)) {
            throw new IntegerValidatorException(String.format("%d musi się różnić od %d", firstId, secondId));
        }
    }
}
