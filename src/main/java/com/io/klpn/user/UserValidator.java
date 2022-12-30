package com.io.klpn.user;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.user.dtos.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.io.klpn.basic.ValidatorService.*;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final ValidatorService validatorService;

    public User createUser(UserCreateDto user) {
        validatorService.validateString("First name", user.firstName(), NAME_REGEX, MAX_LENGTH_45);
        validatorService.validateString("Last name", user.lastName(), NAME_REGEX, MAX_LENGTH_45);
        validatorService.validateString("Email", user.email(), EMAIL_REGEX, MAX_LENGTH_45);
        validatorService.validateString("Password", user.password(), PASSWORD_REGEX, MAX_LENGTH_255);
        validatorService.validatePhoneNumber(user.phoneNumber());
        return new User(user.firstName(), user.lastName(), user.email(), user.password(), user.phoneNumber());
    }

}
