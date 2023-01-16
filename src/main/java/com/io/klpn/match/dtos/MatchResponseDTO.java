package com.io.klpn.match.dtos;

import lombok.Builder;

public record MatchResponseDTO(String firstTeamName,
                               String secondTeamName,
                               String date,
                               Integer firstTeamGoals,
                               Integer secondTeamGoals,
                               String refereeFullName,
                               Long matchId) {

    @Builder
    public MatchResponseDTO {}

}
