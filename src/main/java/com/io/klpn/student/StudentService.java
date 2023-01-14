package com.io.klpn.student;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.basic.exceptions.TeamPlayersNumberException;
import com.io.klpn.student.dtos.PlayerAndStatsDTO;
import com.io.klpn.student.enums.StudentMapper;
import com.io.klpn.team.Team;
import com.io.klpn.team.TeamRepository;
import com.io.klpn.team.TeamService;
import com.io.klpn.team.dtos.TeamDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentService {

    final StudentRepository studentRepository;
    final StudentValidator studentValidator;
    final StudentEditor studentEditor;
    final StudentMapper studentMapper;
    final TeamService teamService;

    final TeamRepository teamRepository;

    public ErrorsListDTO createStudent(Long id, Integer indexNumber) {
        var errorsList = new ErrorsListDTO();

        try {
            var student = studentValidator.createStudent(id, indexNumber);
            studentRepository.save(student);
        }
        catch (AlreadyExistsException | IntegerValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }

        return errorsList;
    }

    public Student getStudentById(Long id) {
        return studentRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Student z podanym id: %d nie istnieje. ", id)));
    }

    // Tylko student ma mieć dostępny ten widok więc póki co żadnej error listy i walidacji nie wysyłam
    public TeamDto getTeamByStudentId(Long id) {
        Student student = studentRepository.findEntityById(id);
        var team = teamService.getTeamById(student.getTeam().getId());
        var teamPlayers = studentRepository.findAllByTeam_Id(student.getTeam().getId());
        var playersWithStats =
                teamPlayers.stream()
                        .map(studentMapper::mapToPlayerAndStatsDto)
                        .sorted(
                                Comparator.comparing(
                                                PlayerAndStatsDTO::motmAmount,
                                                Comparator.nullsFirst(Comparator.naturalOrder())
                                        )
                                        .reversed()
                        )
                        .toList();
        return new TeamDto(team.getId(), team.getName(), playersWithStats);
    }

    public Student getStudentByIndexNumber(Integer indexNumber) {
        return studentRepository.getStudentByIndexNumber(indexNumber);
    }

    public ErrorsListDTO updateStudentField(UpdateDto updateDto) {
        var errorsList = new ErrorsListDTO();

        try {
            var student = getStudentById(updateDto.id());
            studentEditor.changeStudentFieldValue(student, updateDto.fieldName(), updateDto.value());
            studentRepository.save(student);
        }
        catch (NoSuchElementException | StringValidatorException | IntegerValidatorException |
               NullPointerException | IllegalArgumentException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public ErrorsListDTO deleteStudentFromTeam(Long id) {
        var errorsList = new ErrorsListDTO();
        try {
            var student = getStudentById(id);
            Team team = teamRepository.findEntityById(student.getTeam().getId());
            if(teamRepository.getTeamSize(team) > 8) {
                student.setTeam(null);
                studentRepository.save(student);
            }
            else {
                throw new TeamPlayersNumberException("Twój zespół musi liczyć conajmniej 8 zawodników !");
            }
        }
        catch (NoSuchElementException | StringValidatorException | IntegerValidatorException |
               NullPointerException | IllegalArgumentException | TeamPlayersNumberException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public ErrorsListDTO deleteStudentById(Long id) {
        var errorsListDTO = new ErrorsListDTO();
        try {
            studentValidator.deleteStudentById(id);
        }
        catch (NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }

}
