package com.io.klpn.transfer;

import com.io.klpn.student.StudentValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

// TODO -- add errors handling
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferValidator {

    final TransferRepository transferRepository;
    final StudentValidator studentValidator;

    public Transfer createHeadForHeadTransfer(Long firstStudentId, Long secondStudentId){
        return new Transfer(firstStudentId, secondStudentId, null, null);
    }

    public void validateStudentBeforeTransfer(Integer indexNumber) {
        studentValidator.existsInDatabaseByIndexNumber(indexNumber);
        studentValidator.checkIfStudentIsAccepted(indexNumber);
        studentValidator.checkIfStudentIsAssignedToAnotherTeam(indexNumber);
    }

    public Transfer createSingleTransfer(Long firstTeamId, Long secondStudentId){
        return new Transfer(null, secondStudentId, firstTeamId, null);
    }

    public void deleteTransfer(Long id){
        if(!transferRepository.existsById(id))
            throw new NoSuchElementException(String.format("Transfer with id: %d does not exist. ", id));
        transferRepository.deleteById(id);
    }

}
