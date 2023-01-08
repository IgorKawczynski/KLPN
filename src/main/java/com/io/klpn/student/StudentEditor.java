package com.io.klpn.student;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.student.enums.Position;
import com.io.klpn.student.enums.Role;
import com.io.klpn.team.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentEditor {

    final ValidatorService validatorService;
    final StudentValidator studentValidator;
    final TeamService teamService;

    public void changeStudentFieldValue(Student student, String fieldName, Object value) {
        validatorService.isNull(fieldName, value);
        switch (fieldName) {
            case "indexNumber" -> {
                var index = (Integer) value;
                studentValidator.validateIndexNumber(index);
                student.setIndexNumber(index);
            }
            case "role" -> {
                var role = Role.getRole((String) value);
                student.setRole(role);
            }
            case "position" -> {
                var position = Position.getPosition((String) value);
                student.setPosition(position);
            }
            case "tshirtNumber" -> {
                var tshirtNumber = (Integer) value;
                studentValidator.validateTshirtNumber(tshirtNumber);
                student.setTshirtNumber(tshirtNumber);
            }
            case "isAccepted" -> {
                var isAccepted = (Boolean) value;
                student.setIsAccepted(isAccepted);
            }
            case "motmAmount" -> {
                student.increaseMotmAmount();
            }
            case "team" -> {
                var teamId = (Integer) value;
                var team = teamService.getTeamById(teamId.longValue());
                student.setTeam(team);
            }
            default -> throw new IllegalArgumentException("Invalid field name.");
        }

    }
}
