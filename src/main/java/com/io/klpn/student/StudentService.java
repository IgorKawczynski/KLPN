package com.io.klpn.student;

import com.io.klpn.basic.ErrorsListDto;
import com.io.klpn.basic.exceptions.AlreadyExistException;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentValidator studentValidator;

    public ErrorsListDto createStudent(Long id, Integer indexNumber) {
        var errorsList = new ErrorsListDto();

        try {
            var student = studentValidator.createStudent(id, indexNumber);
            studentRepository.save(student);
            errorsList.addSuccessfullMessage(String.format("Successfully added student with %d index number to database.", indexNumber));
        }
        catch (AlreadyExistException | IntegerValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }

        return errorsList;
    }

}
