package com.io.klpn.team;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.student.StudentValidator;
import com.io.klpn.team.dtos.TeamCreateDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.io.klpn.basic.ValidatorService.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamValidator {

    final ValidatorService validatorService;
    final TeamRepository teamRepository;
    final StudentValidator studentValidator;

    public Team createTeam(TeamCreateDTO teamDTO) {
        validateTeamName(teamDTO.name());
        validateTeamLength(teamDTO.indexes());
        isAnyRefreeGiven(teamDTO.referees());
        studentValidator.checkIfStudentsAreAccepted(teamDTO.indexes());
        studentValidator.checkIfAnyStudentIsAssignedToAnotherTeam(teamDTO.indexes());
        return new Team(teamDTO.name());
    }

    public void isAnyRefreeGiven(List<Boolean> referees) {
        var refereeGiven = false;
        for (Boolean element: referees) {
            if (element != null && element == true) {
                refereeGiven = true;
                break;
            }
        }
        if (refereeGiven) return;
        // jeśli nie znaleziono sędziego, rzucam wyjątek
        throw new NullPointerException("Każda drużyna musi wyznaczyć przynajmniej jednego zawodnika na sędziego.");
    }

    public void validateTeamLength(List<Integer> indexes) {
        if(Objects.isNull(indexes)) {
            throw new NullPointerException("Proszę podać zawodników do drużyny.");
        }
        if(indexes.size() < 8 || indexes.size() > 12) {
            throw new IllegalStateException("Drużyna musi posiadać minimum 8 lub maksymalnie 12 zawodników!");
        }
    }

    public void validateTeamName(String name) {
        validatorService.isNull("Name", name);
        validatorService.validateString("Name", name, NAME_REGEX, MAX_LENGTH_45);
        if (teamRepository.existsByName(name)) {
            throw new AlreadyExistsException("Nazwa drużyny jest zajęta, proszę podać inną!");
        }
    }

    public void teamSizeLessThanTwelve(Long teamId) {
        var team = teamRepository.getReferenceById(teamId);
        var teamSize = teamRepository.getTeamSize(team).intValue();
        if (teamSize >= 12) {
            throw new IllegalStateException("Drużyna nie może mieć więcej niż 12 zawodników!");
        }
    }
}
