package com.io.klpn.student;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentService {

    final StudentRepository studentRepository;
    final StudentValidator studentValidator;
    final StudentEditor studentEditor;

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

    public Student getStudentById(Long id) {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Student with id: %d does not exist. ", id)));
    }

    public ErrorsListDTO updateStudentField(UpdateDto updateDto) {
        var errorsList = new ErrorsListDTO();

        try {
            var student = getStudentById(updateDto.id());
            studentEditor.changeStudentFieldValue(student, updateDto.fieldName(), updateDto.value());
            studentRepository.save(student);
        }
        catch (NoSuchElementException | StringValidatorException | IntegerValidatorException |
                IllegalArgumentException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public ErrorsListDTO deleteStudentById(Long id) {
        var errorsListDTO = new ErrorsListDTO();
        try {
            studentValidator.deleteStudentById(id);
        }
        catch (NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }

}
