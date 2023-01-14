package com.io.klpn.student.enums;

import com.io.klpn.match_statistic.MatchStatisticService;
import com.io.klpn.student.Student;
import com.io.klpn.student.dtos.PlayerAndStatsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final MatchStatisticService matchStatisticService;

    public PlayerAndStatsDTO mapToPlayerAndStatsDto(Student student){
        var fullName = getStudentFullName(student);
        var playerStats = matchStatisticService.getPlayerStatsDtoByPlayerId(student.getId());
        if(student.getPosition() != null) {
            return new PlayerAndStatsDTO(student.getId(), fullName, student.getPosition().getPositionType(), playerStats.goals(),
                    playerStats.assists(), playerStats.redCards(),
                    playerStats.ownGoals(), student.getMotmAmount());
        }
        return new PlayerAndStatsDTO(student.getId(), fullName, null, playerStats.goals(),
                playerStats.assists(), playerStats.redCards(),
                playerStats.ownGoals(), student.getMotmAmount());
    }

    private String getStudentFullName(Student student) {
        var builder = new StringBuilder();
        builder.append(student.getUser().getFirstName());
        builder.append(' ');
        builder.append(student.getUser().getLastName());
        builder.append(' ');
        builder.append('#');
        builder.append(student.getTshirtNumber());
        return builder.toString();
    }

}
