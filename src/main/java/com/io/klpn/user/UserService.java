package com.io.klpn.user;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.security.SessionRegistry;
import com.io.klpn.student.StudentService;
import com.io.klpn.user.dtos.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

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

    // TODO+ -- walidacja z loginem do UserValidator i ustawienie walidatorów na email + password...
    public UserLoginResponseDTO login(UserLoginRequestDTO user) {
        UserLoginResponseDTO response = new UserLoginResponseDTO(new ErrorsListDTO());
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(user.email(), user.password()));
            final String sessionId = sessionRegistry.registerSession(user.email());
            response.setSessionId(sessionId);
            response.setId(userRepository.findByEmail(user.email()).getId());
        }
        catch (BadCredentialsException | InternalAuthenticationServiceException exception) {
            if (Objects.isNull(user.email()) ) {
                response.addToErrorList("Podaj email !");
            }
            if (Objects.isNull(user.email()) ) {
                response.addToErrorList("Podaj hasło !");
            }
            if ( !Objects.isNull(user.email()) && !userValidator.emailContainsAtSign(user.email()) ) {
                response.addToErrorList("Email musi zawierać znak '@'");
            }
            response.addToErrorList("Podałeś zły email / hasło, spróbuj jeszcze raz !");
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
