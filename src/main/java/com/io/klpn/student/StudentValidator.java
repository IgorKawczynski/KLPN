package com.io.klpn.student;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.basic.exceptions.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentValidator {

    private final StudentRepository studentRepository;
    private final ValidatorService validatorService;
    private final static Integer MAX_INDEX_VALUE = 999999;
    private final static Integer MIN_INDEX_VALUE = 100000;

    public Student createStudent(Long id, Integer indexNumber) {
        checkIfExistInDatabase(id, indexNumber);
        validatorService.validateIntegerBiggerThan(indexNumber, MAX_INDEX_VALUE);
        validatorService.validateIntegerLessThan(indexNumber, MIN_INDEX_VALUE);
        return new Student(id, indexNumber);
    }

    public void checkIfExistInDatabase(Long id, Integer indexNumber) {
        if (studentRepository.existsById(id) || studentRepository.existsByIndexNumber(indexNumber)) {
            throw new AlreadyExistException("Student with given id or index number already exist!");
        }
    }

}
