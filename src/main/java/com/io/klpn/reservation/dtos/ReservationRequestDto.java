package com.io.klpn.reservation.dtos;

import com.io.klpn.user.User;
import lombok.Builder;

import java.time.LocalDateTime;

public record ReservationRequestDto(Integer pitch, LocalDateTime date, User user) {

    @Builder
    public ReservationRequestDto {}
}
