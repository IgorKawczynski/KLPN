package com.io.klpn.team;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.basic.exceptions.StringValidatorException;
import com.io.klpn.student.Student;
import com.io.klpn.student.StudentPlayerDTO;
import com.io.klpn.student.StudentRepository;
import com.io.klpn.student.dtos.PlayerAndStatsDTO;
import com.io.klpn.student.enums.Role;
import com.io.klpn.student.enums.StudentMapper;
import com.io.klpn.team.dtos.TeamCreateDTO;
import com.io.klpn.team.dtos.TeamDto;
import com.io.klpn.team.dtos.TeamToAcceptDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamService {

    final TeamRepository teamRepository;
    final TeamValidator teamValidator;
    final TeamEditor teamEditor;
    final StudentRepository studentRepository;
    final StudentMapper studentMapper;
    final Comparator<Student> studentIndexNumber = Comparator.comparingInt(Student::getIndexNumber);
    final Comparator<StudentPlayerDTO> playerIndexNumber = Comparator.comparingInt(StudentPlayerDTO::indexNumber);

    public ErrorsListDTO createTeam(TeamCreateDTO teamDTO){
        var errorsListDTO = new ErrorsListDTO();
        try {
            var team = teamValidator.createTeam(teamDTO);
            var students = studentRepository.findAllByIndexNumberIn(teamDTO.indexes());
            sortLists(students, teamDTO.players()); // bardzo wazne, inne metody zależą od tego sortowania
            isEveryGivenIndexNumberFound(students, teamDTO.players());
            assignStudentsPositions(students, teamDTO.players());
            assignStudentsRoles(students, teamDTO.players());
            assignStudentsTshirtNumbers(students);
            assignTeamCaptain(teamDTO.captainId());
            var createdTeam = teamRepository.saveAndFlush(team);
            assignStudentsToTeam(students, createdTeam);
        }
        catch(AlreadyExistsException | StringValidatorException | NullPointerException |
              IllegalStateException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }

    private void sortLists(List<Student> students, List<StudentPlayerDTO> players) {
        students.sort(studentIndexNumber);
        players.sort(playerIndexNumber);
    }

    private void assignStudentsPositions(List<Student> students, List<StudentPlayerDTO> players) {
        int counter = 0;
        while(counter < students.size()) {
            var student = students.get(counter);
            var position = players.get(counter).position();
            student.setPosition(position);
            counter++;
        }
    }

    private void assignStudentsRoles(List<Student> students, List<StudentPlayerDTO> players) {
        int counter = 0;
        while(counter < students.size()) {
            var student = students.get(counter);
            var isReferee = players.get(counter).isReferee();
            if (isReferee == true) {
                student.setRole(Role.REFEREE);
            } else {
                student.setRole(Role.PLAYER);
            }
            counter++;
        }
    }

    private void assignStudentsTshirtNumbers(List<Student> students) {
        int counter = 0;
        var listOfUsedNumbers = new ArrayList<>();
        while(counter < students.size()) {
            var student = students.get(counter);
            Random random = new Random();
            var nextRandom = random.nextInt(1, 99);
            while(listOfUsedNumbers.contains(nextRandom)) {
                nextRandom = random.nextInt(1, 99);
            }
            listOfUsedNumbers.add(nextRandom);
            student.setTshirtNumber(nextRandom);
            counter++;
        }
    }

    private void assignTeamCaptain(Long captainId) {
        var student = studentRepository.findById(captainId).get();
        student.setRole(Role.CAPTAIN);
        studentRepository.saveAndFlush(student);
    }

    private void assignStudentsToTeam(List<Student> students, Team team) {
        students.forEach(student -> student.setTeam(team));
        studentRepository.saveAll(students);
    }

    private void isEveryGivenIndexNumberFound(List<Student> students, List<StudentPlayerDTO> players) {
        if(students.size() == players.size()) return;

        ArrayList<Integer> notFoundIndexes = new ArrayList<>();
        var foundIndexes = students.stream().mapToInt(Student::getIndexNumber).boxed().toList();

        players.forEach(player -> {
            if(!foundIndexes.contains(player.indexNumber())){
                notFoundIndexes.add(player.indexNumber());
            }
        });

        throw new IllegalStateException("Nie znaleziono podanych indeksow o numerach: " + notFoundIndexes);
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

    public ErrorsListDTO deleteTeamById(Long teamId){
        var errorsListDTO = new ErrorsListDTO();
        try {
            var team = getTeamById(teamId);
            var teamPlayers = studentRepository.findAllByTeam_Id(teamId);
            teamPlayers.forEach(student -> student.setTeam(null));
            studentRepository.saveAll(teamPlayers);
            teamRepository.deleteById(teamId);
        }
        catch(NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }

    public List<TeamToAcceptDTO> getTeamsToAccept() {
        return teamRepository.findTeamsToAccept();
    }

    public TeamDto getTeamDetails(Long teamId) {
        var team = getTeamById(teamId);
        var teamPlayers = studentRepository.findAllByTeam_Id(teamId);
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
        return new TeamDto(teamId, team.getName(), playersWithStats);
    }

}
