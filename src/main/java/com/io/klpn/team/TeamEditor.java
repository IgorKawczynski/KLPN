package com.io.klpn.team;

import com.io.klpn.basic.ValidatorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamEditor {


    final ValidatorService validatorService;
    final TeamValidator teamValidator;
    public void changeTeamFieldValue(Team team, String fieldName, Object value) {
        validatorService.isNull(fieldName, value);
        switch (fieldName) {
            case "name" -> {
                var name = (String) value;
                teamValidator.validateTeamName(name);
                team.setName(name);
            }
            case "isAccepted" -> {
                var isAccepted = (Boolean) value;
                team.setIsAccepted(isAccepted);
            }
        }
    }

}
