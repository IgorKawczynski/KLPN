package com.io.klpn.user;

import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.user.dtos.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public String registerUser(UserCreateDto userCreateDto) {
        String message = "Successfully registered an user";

        try {
            var user = userValidator.createUser(userCreateDto);
            userRepository.save(user);
        }
        catch(StringValidatorException exception) {
            message = exception.getMessage();
        }

        return message;
    }

}
