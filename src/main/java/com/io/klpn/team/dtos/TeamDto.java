package com.io.klpn.team.dtos;

import com.io.klpn.student.dtos.PlayerAndStatsDTO;

import java.util.List;

public record TeamDto(Long teamId, String Name, List<PlayerAndStatsDTO> playerAndStatsDTOs) {
}
