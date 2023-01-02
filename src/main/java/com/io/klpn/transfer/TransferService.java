package com.io.klpn.transfer;

import com.io.klpn.basic.ErrorsListDto;
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

    public void createHeadForHeadTransfer(Long firstStudentId, Long secondStudentId){
        transferRepository.save(transferValidator.createHeadForHeadTransfer(firstStudentId, secondStudentId));
    }

    public void createSingleTransfer(Long firstTeamId, Long secondStudentId){
        transferRepository.save(transferValidator.createSingleTransfer(firstTeamId, secondStudentId));
    }

    public Transfer getTransferById(Long id){
        return transferRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Transfer with id: %d does not exist. ", id)));
    }

    public Page<Transfer> getAllTransfers(Integer page){
        Pageable paginated = PageRequest.of(page, 20);
        return transferRepository.findAll(paginated);
    }

    public ErrorsListDto deleteTransferById(Long id){
        var errorsListDTO = new ErrorsListDto();
        try {
            transferValidator.deleteTransfer(id);
        }
        catch(NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }
}
