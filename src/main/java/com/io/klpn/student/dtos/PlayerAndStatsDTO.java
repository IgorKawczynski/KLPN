package com.io.klpn.student.dtos;

public record PlayerAndStatsDTO(Long playerId, String fullName, String position, Integer goals,
                                Integer assists, Integer redCards,
                                Integer ownGoals, Integer motmAmount) {
}
