package com.io.klpn.match_date_edit.dtos;

import java.time.LocalDateTime;

public record MatchDateEditDTO(Integer pitch, LocalDateTime requestedDate, Long captainId, Long reservationId) {
}
