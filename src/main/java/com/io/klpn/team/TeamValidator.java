package com.io.klpn.team;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

import static com.io.klpn.basic.ValidatorService.*;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamValidator {

    final ValidatorService validatorService;
    final TeamRepository teamRepository;

    public Team createTeam(Team team) {
        validateTeamName(team.getName());
        return new Team(team.getName());
    }

    public void deleteTeamById(Long id) {
        if(!teamRepository.existsById(id)) {
            throw new NoSuchElementException(String.format("Team with id: %d does not exist. ", id));
        }
        teamRepository.deleteById(id);
    }

    public void validateTeamName(String name) {
        validatorService.isNull("Name", name);
        validatorService.validateString("Name", name, NAME_REGEX, MAX_LENGTH_45);
        if (teamRepository.existsByName(name)) {
            throw new AlreadyExistsException("Team with this name already exists, choose another one!");
        }
    }

}
