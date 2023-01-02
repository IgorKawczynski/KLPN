package com.io.klpn.transfer;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.transfer.dto.TransferRequestDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransferController {

    final TransferService transferService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Page<Transfer> getAllTransfers(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return transferService.getAllTransfers(page);
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

    @PostMapping(path = "/single", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSingleTransfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        transferService.createSingleTransfer(transferRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorsListDTO deleteTransferById(@PathVariable Long id) {
        return transferService.deleteTransferById(id);
    }

}
