package com.io.klpn.match.dtos;

import lombok.Builder;

public record MatchForRefereeResponseDTO(String teams, String date, Long matchId) {

    @Builder
    public MatchForRefereeResponseDTO {}
}
