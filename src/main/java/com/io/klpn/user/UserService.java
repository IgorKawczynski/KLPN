package com.io.klpn.user;

import com.io.klpn.basic.ErrorsListDto;
import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.student.StudentService;
import com.io.klpn.user.dtos.UserCreateDto;
import com.io.klpn.user.dtos.UserUpdateToStudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final StudentService studentService;

    public ErrorsListDto registerUser(UserCreateDto userCreateDto) {
        var errorsList = new ErrorsListDto();

        try {
            var user = userValidator.createUser(userCreateDto);
            userRepository.save(user);
            errorsList.addSuccessfullMessage("Successfully registered an user");
        }
        catch (StringValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }

        return errorsList;
    }

    public ErrorsListDto updateToStudent(UserUpdateToStudentDto user) {
        var errorsList = studentService.createStudent(user.id(), user.indexNumber());
        if (errorsList.isListOfErrorsEmpty()) {
            errorsList.addSuccessfullMessage("Successfully updated user to student.");
        }
        return errorsList;
    }

}
