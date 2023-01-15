package com.io.klpn.match_date_edit.dtos;

import java.time.LocalDateTime;

public record MatchDateEditDTO(Integer pitch, LocalDateTime newMatchDate,
                               Long captainId, Long reservationId) {
}
