package com.io.klpn.user;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.student.StudentService;
import com.io.klpn.user.dtos.UserCreateDto;
import com.io.klpn.user.dtos.UserResponseDto;
import com.io.klpn.user.dtos.UserUpdateDto;
import com.io.klpn.user.dtos.UserUpdateToStudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserEditor userEditor;
    private final StudentService studentService;

    public ErrorsListDTO registerUser(UserCreateDto userCreateDto) {
        var errorsList = new ErrorsListDTO();

        try {
            var user = userValidator.createUser(userCreateDto);
            userRepository.save(user);
        }
        catch (StringValidatorException | AlreadyExistsException | NullPointerException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public ErrorsListDTO updateToStudent(UserUpdateToStudentDto user) {
        return studentService.createStudent(user.id(), user.indexNumber());
    }

    public ErrorsListDTO deleteUser(Long userId) {
        var errorsList = new ErrorsListDTO();
        try {
            userRepository.deleteById(userId);
        }
        catch (EmptyResultDataAccessException exception) {
            errorsList.addError("User with given id doesn't exists!");
        }
        return errorsList;
    }

    public UserResponseDto getUserResponseDto(Long userId) {
        return getUserById(userId).toResponseDto();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with given id doesn't exists!"));
    }

    public ErrorsListDTO updateUserField(UserUpdateDto userUpdateDto) {
        var errorsList = new ErrorsListDTO();

        try {
            var user = getUserById(userUpdateDto.id());
            userEditor.changeUserFieldValue(user, userUpdateDto.fieldName(), userUpdateDto.value());
            userRepository.save(user);
        }
        catch (NoSuchElementException | StringValidatorException | IllegalArgumentException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

}
