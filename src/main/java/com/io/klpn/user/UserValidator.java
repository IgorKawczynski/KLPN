package com.io.klpn.user;

import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.user.dtos.UserCreateDto;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private static final String NAME_REGEX = "[\\p{Alpha}\\p{Space}-.']++";
    private static final String EMAIL_REGEX = "[\\p{Alpha}\\p{Digit}]++@[\\p{Alpha}\\p{Digit}]++.[\\p{Alpha}\\p{Digit}]++";
    private static final String PHONE_NUMBER_REGEX = "[\\d]++";
    private static final String PASSWORD_REGEX = "[\\p{Alnum}\\p{Punct}]++";
    private static final Integer MAX_LENGTH_255 = 255;
    private static final Integer MAX_LENGTH_45 = 45;
    private static final Integer MAX_LENGTH_9 = 9;
    private static final Integer MIN_LENGTH_3 = 3;

    public User createUser(UserCreateDto user) {
        validateString("First name", user.firstName(), NAME_REGEX, MAX_LENGTH_45);
        validateString("Last name", user.lastName(), NAME_REGEX, MAX_LENGTH_45);
        validateString("Email", user.email(), EMAIL_REGEX, MAX_LENGTH_45);
        validateString("Password", user.password(), PASSWORD_REGEX, MAX_LENGTH_255);
        validatePhoneNumber(user.phoneNumber());
        return new User(user.firstName(), user.lastName(), user.email(), user.password(), user.phoneNumber());
    }

    private void validateString(String fieldName, String fieldValue, String regex, Integer maxLength) {
        if(!fieldValue.matches(regex)) {
            throw new StringValidatorException(String.format("%s contains illegal characters!", fieldName));
        }
        if(fieldValue.length() > maxLength || fieldValue.length() < MIN_LENGTH_3) {
            throw new StringValidatorException(String.format("%s must be between %d to %d chars length!", fieldName, maxLength, MIN_LENGTH_3));
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if(!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            throw new StringValidatorException("Phone number must contain only digits!");
        }

        if(phoneNumber.length() != MAX_LENGTH_9) {
            throw new StringValidatorException("Phone number must be 9 digits length!");
        }
    }

}
