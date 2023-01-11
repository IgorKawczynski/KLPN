package com.io.klpn.match_statistic.dtos;

import com.io.klpn.match_statistic.enums.Event;

public record MatchStatisticCreateDTO(Event event, Integer minute, Long studentId, Long matchId) {
}
