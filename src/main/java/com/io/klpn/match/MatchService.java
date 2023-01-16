package com.io.klpn.match;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.match.dtos.MatchForStudentResponseDTO;
import com.io.klpn.match.dtos.MatchResponseDTO;
import com.io.klpn.reservation.Reservation;
import com.io.klpn.reservation.ReservationRepository;
import com.io.klpn.student.Student;
import com.io.klpn.student.StudentService;
import com.io.klpn.team.TeamRepository;
import com.io.klpn.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchService {

    final MatchRepository matchRepository;
    final MatchValidator matchValidator;

    final TeamRepository teamRepository;

    final UserRepository userRepository;

    final ReservationRepository reservationRepository;
    final StudentService studentService;

    public ErrorsListDTO createMatch (Match matchToCreate) {
        var errorsList = new ErrorsListDTO();

        try {
            var match = matchValidator.createMatch(matchToCreate);
            matchRepository.save(match);
        }
        catch (IntegerValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public Match getMatchById(Long matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Mecz z podanym id nie istnieje!"));
    }

    public Page<Match> getAllMatches(Integer page){
        Pageable paginated = PageRequest.of(page, 20);
        return matchRepository.findAll(paginated);
    }

    public ErrorsListDTO deleteMatchById(Long id){
        var errorsListDto = new ErrorsListDTO();
        try {
            matchValidator.deleteMatchById(id);
        }
        catch (NoSuchElementException exception) {
            errorsListDto.addError(exception.getMessage());
        }
        return errorsListDto;
    }

    public List<LocalDate> getDatesReservedForAdmin(){
        List<Reservation> reservationList = reservationRepository.getReservationsForAdmin();

        List<LocalDateTime> reservationDates = reservationList.stream().map(Reservation::getDate).distinct().toList();
        TreeSet<LocalDate> uniqueDatesSorted = new TreeSet<>();

        for (LocalDateTime date: reservationDates) {
            uniqueDatesSorted.add(date.toLocalDate());
        }

        return uniqueDatesSorted.stream().toList();
    }

    public List<MatchResponseDTO> getMatchResponseDTOsListByDay(LocalDateTime date){
        LocalDateTime dateEnd = date.plusDays(1);
        List<Reservation> reservationListForAdminAndDay = reservationRepository.getReservationsForAdminAndDate(date, dateEnd);

        List<MatchResponseDTO> matchResponseDTOS = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for(Reservation reservation: reservationListForAdminAndDay){
            var match = matchRepository.findMatchByReservation_Id(reservation.getId());
            if(match != null) {
                var firstTeam = teamRepository.findById(match.getFirstTeamId()).get();
                var secondTeam = teamRepository.findById(match.getSecondTeamId()).get();
                var referee = userRepository.findById(match.getRefereeId()).get();

                String refereeName = referee.getFirstName() + " " + referee.getLastName();

                String formattedDate = reservation.getDate().format(formatter);

                MatchResponseDTO matchResponseDTO =
                        new MatchResponseDTO(firstTeam.getName(), secondTeam.getName(),
                                formattedDate, match.getFirstTeamGoals(),
                                match.getSecondTeamGoals(), refereeName, match.getId());

                matchResponseDTOS.add(matchResponseDTO);
            }

        }
        return matchResponseDTOS;
    }

    public MatchForStudentResponseDTO getMatchForRefereeByMatchId(Long matchId) {
        Match match = matchRepository.findById(matchId).get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        var firstTeam = teamRepository.findById(match.getFirstTeamId()).get();
        var secondTeam = teamRepository.findById(match.getSecondTeamId()).get();
        String teams = firstTeam.getName() + " vs " + secondTeam.getName();

        String result = match.getFirstTeamGoals() + " : " + match.getSecondTeamGoals();

        var reservation = reservationRepository.findById(match.getReservation().getId()).get();
        String formattedDate = reservation.getDate().format(formatter);

        return new MatchForStudentResponseDTO(teams, result, formattedDate, reservation.getId(), matchId);
    }

    public List<MatchForStudentResponseDTO> getMatchesForRefereeByRefereeId(Long refereeId) {
        List<Match> matchesForReferee = matchRepository.getMatchesByRefereeId(refereeId);
        return matchListToMatchForStudentResposneDtoList(matchesForReferee);
    }

    public List<MatchForStudentResponseDTO> matchListToMatchForStudentResposneDtoList(List<Match> matches) {
        List<MatchForStudentResponseDTO> matchForStudentResponseDTOS = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        for (Match match: matches) {
            var firstTeam = teamRepository.findById(match.getFirstTeamId()).get();
            var secondTeam = teamRepository.findById(match.getSecondTeamId()).get();
            String teams = firstTeam.getName() + " vs " + secondTeam.getName();

            String result = match.getFirstTeamGoals() + " : " + match.getSecondTeamGoals();

            var reservation = reservationRepository.findById(match.getReservation().getId()).get();
            String formattedDate = reservation.getDate().format(formatter);

            var matchResponse = new MatchForStudentResponseDTO(teams, result, formattedDate, match.getId(), reservation.getId());
            matchForStudentResponseDTOS.add(matchResponse);
        }

        return matchForStudentResponseDTOS;
    }

    public List<MatchForStudentResponseDTO> getMatchesForStudentTeam(Long studentId) {
        var student = studentService.getStudentById(studentId);
        isStudentAssignedToAnyTeam(student);
        var studentMatches = matchRepository.getMatchesForTeam(student.getTeam().getId());
        return matchListToMatchForStudentResposneDtoList(studentMatches);
    }

    public void isStudentAssignedToAnyTeam(Student student) {
        if(student.getTeam() == null) {
            throw new IllegalArgumentException("Nie jesteś przypisany do żadnej drużyny!");
        }
    }

    public MatchForStudentResponseDTO getMatchForStudentByReservationId(Long reservationId) {
        var reservation = reservationRepository.findById(reservationId).get();
        var match = matchRepository.findMatchByReservation_Id(reservationId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = reservation.getDate().format(formatter);
        var firstTeam = teamRepository.findById(match.getFirstTeamId()).get();
        var secondTeam = teamRepository.findById(match.getSecondTeamId()).get();
        String teams = firstTeam.getName() + " vs " + secondTeam.getName();

        String result = match.getFirstTeamGoals() + " : " + match.getSecondTeamGoals();

        return new MatchForStudentResponseDTO(teams, result, formattedDate, match.getId(), reservation.getId());
    }

}
