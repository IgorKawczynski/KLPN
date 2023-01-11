package com.io.klpn.reservation.dtos;

import lombok.Builder;

import java.time.LocalDateTime;

public record ReservationRequestDto(Long userId, Integer pitch, LocalDateTime date) {

    @Builder
    public ReservationRequestDto {}
}
