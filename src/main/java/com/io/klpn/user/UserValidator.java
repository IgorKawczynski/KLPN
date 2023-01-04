package com.io.klpn.user;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.user.dtos.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.io.klpn.basic.ValidatorService.*;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final ValidatorService validatorService;
    private final UserRepository userRepository;

    public User createUser(UserCreateDto user) {
        checkIfExistsInDatabaseByEmail(user.email());
        validatorService.isNull("First name", user.firstName());
        validatorService.validateString("First name", user.firstName(), NAME_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Last name", user.lastName());
        validatorService.validateString("Last name", user.lastName(), NAME_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Email", user.email());
        validatorService.validateString("Email", user.email(), EMAIL_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Password", user.password());
        validatorService.validateString("Password", user.password(), PASSWORD_REGEX, MAX_LENGTH_255);
        validatorService.isNull("Phone Number", user.phoneNumber());
        validatorService.validatePhoneNumber(user.phoneNumber());
        return new User(user.firstName(), user.lastName(), user.email(), user.password(), user.phoneNumber());
    }

    public void checkIfExistsInDatabaseByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("User with given email already exists!");
        }
    }


}
