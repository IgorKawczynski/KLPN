package com.io.klpn.match_statistic.dtos;

import com.io.klpn.match_statistic.enums.Event;
import lombok.Builder;

public record MatchStatisticCreateByIndexDTO(Event event, Integer minute, Long studentIndex, Long matchId) {

    @Builder
    public MatchStatisticCreateByIndexDTO {}
}
