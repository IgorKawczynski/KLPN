package com.io.klpn.student;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentService {

    final StudentRepository studentRepository;
    final StudentValidator studentValidator;

    public ErrorsListDTO createStudent(Long id, Integer indexNumber) {
        var errorsList = new ErrorsListDTO();

        try {
            var student = studentValidator.createStudent(id, indexNumber);
            studentRepository.save(student);
        }
        catch (AlreadyExistsException | IntegerValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }

        return errorsList;
    }

}
