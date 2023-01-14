package com.io.klpn.match.dtos;

import lombok.Builder;

public record MatchForStudentResponseDTO(String teams, String date, Long matchId) {

    @Builder
    public MatchForStudentResponseDTO {}
}
