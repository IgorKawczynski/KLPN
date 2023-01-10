package com.io.klpn.team;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamService {

    final TeamRepository teamRepository;
    final TeamValidator teamValidator;
    final TeamEditor teamEditor;

    public ErrorsListDTO createTeam(Team team){
        var errorsListDTO = new ErrorsListDTO();
        try {
            teamRepository.save(teamValidator.createTeam(team));
        }
        catch(AlreadyExistsException | StringValidatorException | NullPointerException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }

    public Team getTeamById(Long id){
        return teamRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Team z podanym id: %d nie istnieje. ", id)));
    }

    public List<Team> generateTable(){
        return teamRepository
                .findAll(Sort.by(Sort.Direction.DESC, "points"));
    }

    public ErrorsListDTO updateTeamField(UpdateDto updateDto) {
        var errorsList = new ErrorsListDTO();

        try {
            var team = getTeamById(updateDto.id());
            teamEditor.changeTeamFieldValue(team, updateDto.fieldName(), updateDto.value());
            teamRepository.save(team);
        }
        catch (NoSuchElementException | StringValidatorException | IntegerValidatorException |
               NullPointerException | IllegalArgumentException exception) {
            errorsList.addError(exception.getMessage());
        }

        return errorsList;
    }

    public ErrorsListDTO deleteTeamById(Long id){
        var errorsListDTO = new ErrorsListDTO();
        try {
            teamValidator.deleteTeamById(id);
        }
        catch(NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }
}
