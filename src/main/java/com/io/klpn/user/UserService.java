package com.io.klpn.user;

import com.io.klpn.basic.ErrorsListDto;
import com.io.klpn.basic.exceptions.AlreadyExistException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.student.StudentService;
import com.io.klpn.user.dtos.UserCreateDto;
import com.io.klpn.user.dtos.UserUpdateToStudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
        }
        catch (StringValidatorException | AlreadyExistException exception) {
            errorsList.addError(exception.getMessage());
        }

        return errorsList;
    }

    public ErrorsListDto updateToStudent(UserUpdateToStudentDto user) {
        return studentService.createStudent(user.id(), user.indexNumber());
    }

    public ErrorsListDto deleteUser(Long userId) {
        var errorsList = new ErrorsListDto();
        try {
            userRepository.deleteById(userId);
        }
        catch (EmptyResultDataAccessException exception) {
            errorsList.addError("User with given id doesn't exists!");
        }
        return errorsList;
    }
}
