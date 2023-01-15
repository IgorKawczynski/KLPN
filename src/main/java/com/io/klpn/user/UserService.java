package com.io.klpn.user;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.security.SessionRegistry;
import com.io.klpn.student.Student;
import com.io.klpn.student.StudentRepository;
import com.io.klpn.student.StudentService;
import com.io.klpn.user.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserEditor userEditor;
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final AuthenticationManager manager;
    private final SessionRegistry sessionRegistry;

    public ErrorsListDTO registerUser(UserRegisterDTO userRegisterDTO) {
        var errorsList = new ErrorsListDTO();
        try {
            var user = userValidator.createUser(userRegisterDTO);
            userRepository.save(user);
            if(userRegisterDTO.indexNumber() != null) {
                errorsList.addError(updateToStudent(new UserUpdateToStudentDTO(user.getId(), userRegisterDTO.indexNumber())));
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

    public UserLoginResponseDTO login(UserLoginRequestDTO user) {
        UserLoginResponseDTO response = new UserLoginResponseDTO(new ErrorsListDTO());
        try {
            userValidator.loginUser(user);
            manager.authenticate(new UsernamePasswordAuthenticationToken(user.email(), user.password()));
            final String sessionId = sessionRegistry.registerSession(user.email());
            response.setSessionId(sessionId);
            response.setId(userRepository.findByEmail(user.email()).getId());
            response.setName(userRepository.findByEmail(user.email()).getFirstName());
            response.setIsAdmin(userRepository.findByEmail(user.email()).getIsAdmin());
            if(studentRepository.existsById(userRepository.findByEmail(user.email()).getId())) {
                Student student = studentRepository.findEntityById(userRepository.findByEmail(user.email()).getId());
                response.setIsStudent(studentRepository.checkIfStudentIsAcceptedById(userRepository.findByEmail(user.email()).getId()));
                response.setRole(student.getRole());
            }
        }
        catch (StringValidatorException | BadCredentialsException | InternalAuthenticationServiceException | NullPointerException exception) {
            if(exception.getClass() == BadCredentialsException.class) {
                response.addToErrorList("Podałeś zły email / hasło, spróbuj jeszcze raz !");
            }
            else {
                response.addToErrorList(exception.getMessage());
            }
        }
        return response;
    }

    public ErrorsListDTO updateToStudent(UserUpdateToStudentDTO user) {
        return studentService.createStudent(user.userId(), user.indexNumber());
    }

    public ErrorsListDTO deleteUser(Long userId) {
        var errorsList = new ErrorsListDTO();
        try {
            userRepository.deleteById(userId);
        }
        catch (EmptyResultDataAccessException exception) {
            errorsList.addError("User z podanym id nie istnieje!");
        }
        return errorsList;
    }

    public UserResponseDTO getUserResponseDto(Long userId) {
        return getUserById(userId).toResponseDto();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User z podanym id nie istnieje!"));
    }

    public UserEditDTO getUserEditDTOById(Long userId) {
        User user = userRepository.findEntityById(userId);
        return UserEditDTO
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }


    public ErrorsListDTO updateUser(Long id, UserEditDTO updatedUser) {
        ErrorsListDTO errorsListDTO = new ErrorsListDTO();
        try {
            userValidator.editUser(updatedUser);
        }
        catch (StringValidatorException | AlreadyExistsException | NullPointerException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        if(errorsListDTO.isListOfErrorsEmpty()) {
            User userTemp= userRepository.findEntityById(id);
            userTemp.setEmail(updatedUser.email());
            userTemp.setFirstName(updatedUser.firstName());
            userTemp.setLastName(updatedUser.lastName());
            userTemp.setPhoneNumber(updatedUser.phoneNumber());
            userRepository.save(userTemp);
        }
        return errorsListDTO;
    }

    public List<UserToAcceptDTO> getUsersToAccept() {
        return userRepository.findUsersToAccept();
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
