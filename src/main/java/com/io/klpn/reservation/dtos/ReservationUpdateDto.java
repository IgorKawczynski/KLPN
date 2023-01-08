package com.io.klpn.reservation.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;

public record ReservationUpdateDto(Long id, Integer pitch, @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date, Long userId) {

    @Builder
    public ReservationUpdateDto {}
}
