package com.io.klpn.transfer;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.student.StudentRepository;
import com.io.klpn.student.StudentService;
import com.io.klpn.team.TeamRepository;
import com.io.klpn.team.TeamService;
import com.io.klpn.team.TeamValidator;
import com.io.klpn.transfer.dto.SingleTransferDTO;
import com.io.klpn.transfer.dto.TransferRequestDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferService {

    final TransferRepository transferRepository;
    final TransferValidator transferValidator;
    final TeamRepository teamRepository;
    final TeamValidator teamValidator;
    final StudentService studentService;
    final StudentRepository studentRepository;

    public void createHeadForHeadTransfer(TransferRequestDTO transferRequestDTO){
        transferRepository.save(transferValidator.createHeadForHeadTransfer(transferRequestDTO.firstId(), transferRequestDTO.secondId()));
    }

    public ErrorsListDTO createSingleTransfer(SingleTransferDTO singleTransferDTO) {
        var errorsList = new ErrorsListDTO();

        try {
            transferValidator.validateStudentBeforeTransfer(singleTransferDTO.newPlayerIndex());
            var captain = studentService.getStudentById(singleTransferDTO.captainId());
            var captainTeam = teamRepository.getReferenceById(captain.getTeam().getId());
            teamValidator.teamSizeLessThanTwelve(captainTeam.getId());
            var newPlayer = studentService.getStudentByIndexNumber(singleTransferDTO.newPlayerIndex());
            var tranfser = transferValidator.createSingleTransfer(captainTeam.getId(), newPlayer.getId());
            transferRepository.save(tranfser);
            newPlayer.setTeam(captainTeam);
            studentRepository.save(newPlayer);
        }
        catch (IllegalStateException exception) {
            errorsList.addError(exception.getMessage());
        }

        return errorsList;
    }

    public Transfer getTransferById(Long id){
        return transferRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Transfer z podanym id: %d nie istnieje. ", id)));
    }

    public Page<Transfer> getAllTransfers(Integer page){
        Pageable paginated = PageRequest.of(page, 20);
        return transferRepository.findAll(paginated);
    }

    public ErrorsListDTO deleteTransferById(Long id){
        var errorsListDTO = new ErrorsListDTO();
        try {
            transferValidator.deleteTransfer(id);
        }
        catch(NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }
}
