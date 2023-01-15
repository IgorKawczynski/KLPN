package com.io.klpn.match_statistic;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.match.MatchRepository;
import com.io.klpn.match_statistic.dtos.*;
import com.io.klpn.student.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchStatisticService {

    final MatchStatisticRepository matchStatisticRepository;

    final MatchStatisticValidator matchStatisticValidator;
    final StudentRepository studentRepository;
    final MatchStatisticMapper mapper;
    final MatchRepository matchRepository;

    public PlayerStatisticsSummaryDTO getPlayerStatsDtoByPlayerId(Long userId) {
        var student = studentRepository.getReferenceById(userId);
        var playerStats = matchStatisticRepository.findAllMatchStatisticsByUserId(student);
        return mapper.toMatchStatisticSummaryDTO(playerStats);
    }
    public ErrorsListDTO createMatchStatistic(MatchStatisticCreateDTO dto){
        var errorsList = new ErrorsListDTO();

        try {
            var matchStatistic = matchStatisticValidator.createMatchStatistic(dto);
            matchStatisticRepository.save(matchStatistic);
        }
        catch (IntegerValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public ErrorsListDTO createMatchStatisticForStudentIndex(MatchStatisticCreateByIndexDTO dtoWithIndex){
        var errorsList = new ErrorsListDTO();

        Integer index = dtoWithIndex.studentIndex().intValue();
        var student = studentRepository.findStudentByIndexNumber(index);
        var dto = new MatchStatisticCreateDTO(dtoWithIndex.event(), dtoWithIndex.minute(), student.getId(), dtoWithIndex.matchId());

        try {
            var matchStatistic = matchStatisticValidator.createMatchStatistic(dto);
            matchStatisticRepository.save(matchStatistic);

            if(dto.event().isGoal()) {
                addGoalToTeam(student.getTeam().getId(), dto.matchId());
            }

            if(dto.event().isOwnGoal()) {
                addGoalToOppositeTeam(student.getTeam().getId(), dto.matchId());
            }

        }
        catch (IntegerValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public MatchStatistic getMatchStatisticById(Long id){
        return matchStatisticRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(
                        "Statystyka meczowa z podanym id id: %d nie istnieje. ", id))
                );
    }

    public Page<MatchStatistic> getAllMatchStatistics(Integer page){
        Pageable paginated = PageRequest.of(page, 20);
        return matchStatisticRepository.findAll(paginated);
    }

    public ErrorsListDTO deleteMatchStatisticById(Long id) {
        var errorsListDTO = new ErrorsListDTO();

        var matchStatistic = matchStatisticRepository.findById(id).get();

        var student = studentRepository.findById(matchStatistic.getStudent().getId()).get();

        try {
            matchStatisticValidator.deleteMatchStatisticById(id);
            if(matchStatistic.getEvent().isGoal()) {
                subtractGoalFromTeam(student.getTeam().getId(), matchStatistic.getMatch().getId());
            }

            if(matchStatistic.getEvent().isOwnGoal()) {
                subtractGoalFromOppositeTeam(student.getTeam().getId(), matchStatistic.getMatch().getId());
            }
        }
        catch (NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }

    public String polishEvents(String event) {
        if(event.equals("GOAL")) return "Goal";
        if(event.equals("ASSIST")) return "Asysta";
        if(event.equals("OWN_GOAL")) return "Bramka samobójcza";
        if(event.equals("RED_CARD")) return "Czerwona kartka";
        if(event.equals("YELLOW_CARD")) return "Żółta kartka";
        return "";
    }

    public List<MatchStatisticResponseDTO> getMatchStatisticsForMatchId(Long matchId) {
        List<MatchStatistic> matchStatistics = matchStatisticRepository.getMatchStatisticsByMatch_Id(matchId);

        List<MatchStatisticResponseDTO> matchStatisticResponseDTOS = new ArrayList<>();

        for(MatchStatistic matchStatistic: matchStatistics) {
            String event = matchStatistic.getEvent().toString();
            event = polishEvents(event);
            Integer minute = matchStatistic.getMinute();

            var student = studentRepository.findById(matchStatistic.getStudent().getId()).get();
            MatchStatisticResponseDTO matchStatisticResponseDTO = new MatchStatisticResponseDTO(event, minute, student.getIndexNumber(), matchStatistic.getId());

            matchStatisticResponseDTOS.add(matchStatisticResponseDTO);
        }

        matchStatisticResponseDTOS.sort(Comparator.comparingInt(MatchStatisticResponseDTO::minute));

        return matchStatisticResponseDTOS;
    }

        public void changeGoals(Long teamId, Long matchId, Integer value){
        var match = matchRepository.findById(matchId).get();

        if(Objects.equals(teamId, match.getFirstTeamId())) {
            match.setFirstTeamGoals(match.getFirstTeamGoals() + value);
            matchRepository.save(match);
        }
        if(Objects.equals(teamId, match.getSecondTeamId())) {
            match.setSecondTeamGoals(match.getSecondTeamGoals() + value);
            matchRepository.save(match);
        }
    }

    public void addGoalToTeam(Long teamId, Long matchId) {
        changeGoals(teamId, matchId, 1);
    }

    public void subtractGoalFromTeam(Long teamId, Long matchId) {
        changeGoals(teamId, matchId, -1);
    }

    public void changeGoalsInOpposite(Long teamId, Long matchId, Integer value){
        var match = matchRepository.findById(matchId).get();

        if(Objects.equals(teamId, match.getFirstTeamId())) {
            match.setSecondTeamGoals(match.getSecondTeamGoals()  + value);
            matchRepository.save(match);
        }
        if(Objects.equals(teamId, match.getSecondTeamId())) {
            match.setFirstTeamGoals(match.getFirstTeamGoals() + value);
            matchRepository.save(match);
        }
    }

    public void addGoalToOppositeTeam(Long teamId, Long matchId) {
        changeGoalsInOpposite(teamId, matchId, 1);
    }

    public void subtractGoalFromOppositeTeam(Long teamId, Long matchId) {
        changeGoalsInOpposite(teamId, matchId, -1);
    }

}
