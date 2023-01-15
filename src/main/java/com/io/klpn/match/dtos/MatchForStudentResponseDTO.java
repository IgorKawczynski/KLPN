package com.io.klpn.match.dtos;

import lombok.Builder;

public record MatchForStudentResponseDTO(String teams, String result, String date, Long matchId, Long reservationId) {

    @Builder
    public MatchForStudentResponseDTO {}
}
