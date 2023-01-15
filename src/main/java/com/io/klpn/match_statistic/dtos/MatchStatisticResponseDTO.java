package com.io.klpn.match_statistic.dtos;

import lombok.Builder;

public record MatchStatisticResponseDTO(String event, Integer minute, Integer studentIndex, Long id) {

    @Builder
    public MatchStatisticResponseDTO {}
}
