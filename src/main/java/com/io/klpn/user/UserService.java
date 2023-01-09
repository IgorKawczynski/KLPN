package com.io.klpn.user;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.student.StudentService;
import com.io.klpn.user.dtos.UserCreateDto;
import com.io.klpn.user.dtos.UserResponseDto;
import com.io.klpn.user.dtos.UserUpdateToStudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
            if(userCreateDto.indexNumber() != null) {
                errorsList.addError(updateToStudent(new UserUpdateToStudentDto(user.getId(), userCreateDto.indexNumber())));
            }
            if(!errorsList.isListOfErrorsEmpty()) {
                userRepository.delete(user);
            }
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

    public ErrorsListDTO updateUserField(UpdateDto updateDto) {
        var errorsList = new ErrorsListDTO();

        try {
            var user = getUserById(updateDto.id());
            userEditor.changeUserFieldValue(user, updateDto.fieldName(), updateDto.value());
            userRepository.save(user);
        }
        catch (NoSuchElementException | StringValidatorException | IllegalArgumentException |
               NullPointerException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

}
