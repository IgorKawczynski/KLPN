package com.io.klpn.team;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.student.Student;
import com.io.klpn.student.StudentPlayerDTO;
import com.io.klpn.student.StudentRepository;
import com.io.klpn.team.dtos.TeamCreateDTO;
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
    final StudentRepository studentRepository;

    public ErrorsListDTO createTeam(TeamCreateDTO teamDTO){
        var errorsListDTO = new ErrorsListDTO();
        try {
            var team = teamValidator.createTeam(teamDTO);
            var createdTeam = teamRepository.saveAndFlush(team);
            assignStudentsToTeam(teamDTO.indexes(), createdTeam);
            // TODO : przypisz pozycję oraz role dla każdego podanego studenta
            // TODO : w sesji przekaż id studenta i mu przypisz rolę kapitana drużyny
        }
        catch(AlreadyExistsException | StringValidatorException | NullPointerException |
              IllegalStateException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }

    public void assignStudentsToTeam(List<Integer> indexNumbers, Team team) {
        var students = studentRepository.findAllByIndexNumberIn(indexNumbers);
        students.forEach(student -> student.setTeam(team));
        studentRepository.saveAll(students);
    }

    public Team getTeamById(Long id){
        return teamRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Drużyna z podanym id: %d nie istnieje. ", id)));
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
