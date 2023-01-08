package com.io.klpn.user;

import com.io.klpn.basic.ValidatorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import static com.io.klpn.basic.ValidatorService.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEditor {

    final ValidatorService validatorService;

    public void changeUserFieldValue(User user, String fieldName, String value){
        validatorService.isNull(fieldName, value);
        switch (fieldName) {
            case "firstName" -> {
                validatorService.validateString("First name", value, NAME_REGEX, MAX_LENGTH_45);
                user.setFirstName(value);
            }
            case "lastName" -> {
                validatorService.validateString("Last name", value, NAME_REGEX, MAX_LENGTH_45);
                user.setLastName(value);
            }
            case "email" -> {
                validatorService.validateString("Email", value, EMAIL_REGEX, MAX_LENGTH_45);
                user.setEmail(value);
            }
            case "password" -> {
                validatorService.validateString("Password", value, PASSWORD_REGEX, MAX_LENGTH_255);
                user.setPassword(value);
            }
            case "phoneNumber" -> {
                validatorService.validatePhoneNumber(value);
                user.setPhoneNumber(value);
            }
            default -> throw new IllegalArgumentException("Invalid field name.");
        }
    }

}
