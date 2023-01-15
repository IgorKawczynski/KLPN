package com.io.klpn.user;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.user.dtos.UserEditDTO;
import com.io.klpn.user.dtos.UserLoginRequestDTO;
import com.io.klpn.user.dtos.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.io.klpn.basic.ValidatorService.*;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final ValidatorService validatorService;
    private final UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(UserRegisterDTO user) {
        checkIfExistsInDatabaseByEmail(user.email());
        validatorService.isNull("Imię", user.firstName());
        validatorService.validateString("Imię", user.firstName(), NAME_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Nazwisko", user.lastName());
        validatorService.validateString("Nazwisko", user.lastName(), NAME_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Email", user.email());
        validatorService.emailContainsAtSign(user.email());
        validatorService.validateString("Email", user.email(), EMAIL_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Hasło", user.password());
        validatorService.validateString("Hasło", user.password(), PASSWORD_REGEX, MAX_LENGTH_255);
        validatorService.isNull("Numer Telefonu", user.phoneNumber());
        validatorService.validatePhoneNumber(user.phoneNumber());
        return new User(user.firstName(), user.lastName(), user.email(), this.encryptPassword(user.password()), user.phoneNumber());
    }

    public void loginUser(UserLoginRequestDTO user) {
        validatorService.isNull("Email", user.email());
        validatorService.emailContainsAtSign(user.email());
        validatorService.validateString("Email", user.email(), EMAIL_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Hasło", user.password());
        validatorService.validateString("Hasło", user.password(), PASSWORD_REGEX, MAX_LENGTH_255);
        checkIfExists(user.email());

    }

    public void editUser(UserEditDTO user) {
        validatorService.isNull("Imię", user.firstName());
        validatorService.validateString("Imię", user.firstName(), NAME_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Nazwisko", user.lastName());
        validatorService.validateString("Nazwisko", user.lastName(), NAME_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Email", user.email());
        validatorService.emailContainsAtSign(user.email());
        validatorService.validateString("Email", user.email(), EMAIL_REGEX, MAX_LENGTH_45);
        validatorService.isNull("Numer Telefonu", user.phoneNumber());
        validatorService.validatePhoneNumber(user.phoneNumber());
    }

    public void checkIfExists(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new BadCredentialsException("Nie ma użytkownika o podanym e-mailu !");
        }
    }

    public void checkIfExistsInDatabaseByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Użytkownik z takim e-mailem już istnieje !");
        }
    }

    private String encryptPassword(String password){
        return password = passwordEncoder.encode(password);
    }
}
