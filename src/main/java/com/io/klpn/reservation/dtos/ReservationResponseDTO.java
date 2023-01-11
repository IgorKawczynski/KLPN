package com.io.klpn.reservation.dtos;

import lombok.Builder;

public record ReservationResponseDTO(Long userId, Integer pitch, String date, Long id) {

    @Builder
    public ReservationResponseDTO {}
}
