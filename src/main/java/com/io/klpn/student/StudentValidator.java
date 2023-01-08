package com.io.klpn.student;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

import static com.io.klpn.basic.ValidatorService.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentValidator {

    final StudentRepository studentRepository;
    final ValidatorService validatorService;

    public Student createStudent(Long id, Integer indexNumber) {
        checkIfExistInDatabase(id, indexNumber);
        validateIndexNumber(indexNumber);
        return new Student(id, indexNumber);
    }

    public void checkIfExistInDatabase(Long id, Integer indexNumber) {
        if (studentRepository.existsById(id) || studentRepository.existsByIndexNumber(indexNumber)) {
            throw new AlreadyExistsException("Student with given id or index number already exist!");
        }
    }

    public void validateIndexNumber(Integer indexNumber) {
        validatorService.validateIntegerBiggerThan(indexNumber, MAX_INDEX_VALUE);
        validatorService.validateIntegerLessThan(indexNumber, MIN_INDEX_VALUE);
    }

    public void validateTshirtNumber(Integer tshirtNumber) {
        validatorService.validateIntegerLessThan(tshirtNumber, MIN_TSHIRT_NUMBER);
        validatorService.validateIntegerBiggerThan(tshirtNumber, MAX_TSHIRT_NUMBER);
    }

    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Student with id: %d does not exist. ", id));
        }
        studentRepository.deleteById(id);
    }

}
