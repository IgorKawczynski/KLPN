package com.io.klpn.transfer;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.transfer.dto.SingleTransferDTO;
import com.io.klpn.transfer.dto.SingleTransferResultDTO;
import com.io.klpn.transfer.dto.TransferRequestDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferController {

    final TransferService transferService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<SingleTransferResultDTO> getAllTransfers() {
        return transferService.getAllTransfers();
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public Transfer getTransferById(@RequestParam("id") Long id) {
        return transferService.getTransferById(id);
    }

    @PostMapping(path = "/headForHead", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createHeadForHeadTransfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        transferService.createHeadForHeadTransfer(transferRequestDTO);
    }

    @PostMapping("/single")
    public ErrorsListDTO createSingleTransfer(@RequestBody SingleTransferDTO singleTransferDTO) {
        return transferService.createSingleTransfer(singleTransferDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorsListDTO deleteTransferById(@PathVariable Long id) {
        return transferService.deleteTransferById(id);
    }

}
