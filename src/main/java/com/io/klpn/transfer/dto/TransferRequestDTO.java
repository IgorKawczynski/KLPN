package com.io.klpn.transfer.dto;

import lombok.Builder;

public record TransferRequestDTO(Long firstId, Long secondId) {

    @Builder
    public TransferRequestDTO { }
}
