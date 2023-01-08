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

    public void changeUserFieldValue(User user, String fieldName, Object value){
        validatorService.isNull(fieldName, value);
        switch (fieldName) {
            case "firstName" -> {
                var firstName = (String) value;
                validatorService.validateString("First name", firstName, NAME_REGEX, MAX_LENGTH_45);
                user.setFirstName(firstName);
            }
            case "lastName" -> {
                var lastName = (String) value;
                validatorService.validateString("Last name", lastName, NAME_REGEX, MAX_LENGTH_45);
                user.setLastName(lastName);
            }
            case "email" -> {
                var email = (String) value;
                validatorService.validateString("Email", email, EMAIL_REGEX, MAX_LENGTH_45);
                user.setEmail(email);
            }
            case "password" -> {
                var password = (String) value;
                validatorService.validateString("Password", password, PASSWORD_REGEX, MAX_LENGTH_255);
                user.setPassword(password);
            }
            case "phoneNumber" -> {
                var phoneNumber = (String) value;
                validatorService.validatePhoneNumber(phoneNumber);
                user.setPhoneNumber(phoneNumber);
            }
            default -> throw new IllegalArgumentException("Invalid field name.");
        }
    }

}
