package com.io.klpn.student;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Component;

import java.util.List;
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
            throw new AlreadyExistsException("Student z podanym id bądź numerem albumu już istnieje!");
        }
    }

    public void existsInDatabaseByIndexNumber(Integer indexNumber) {
        if (!studentRepository.existsByIndexNumber(indexNumber)) {
            throw new IllegalStateException("Nie znaleziono studenta z podanym numerem albumu!");
        }
    }

    public void checkIfStudentsAreAccepted(List<Integer> indexNumbers) {
        indexNumbers.forEach(this::checkIfStudentIsAccepted);
    }

    public void checkIfStudentIsAccepted(Integer indexNumber) {
        if (!studentRepository.checkIfStudentIsAccepted(indexNumber)) {
            throw new IllegalStateException(String.format("Student o indeksie %d jeszcze nie został zaakceptowany przez Administratora.", indexNumber));
        }
    }

    public void checkIfAnyStudentIsAssignedToAnotherTeam(List<Integer> indexNumber) {
        indexNumber.forEach(this::checkIfStudentIsAssignedToAnotherTeam);
    }

    public void checkIfStudentIsAssignedToAnotherTeam(Integer indexNumber) {
        if (studentRepository.checkIfStudentIsAssignedToAnotherTeam(indexNumber)){
            throw new IllegalStateException(String.format("Student o indeksie %d jest już przypisany do innej drużyny!", indexNumber));
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
            throw new NoSuchElementException(String.format("Student z podanym id: %d nie istnieje. ", id));
        }
        studentRepository.deleteById(id);
    }

}
