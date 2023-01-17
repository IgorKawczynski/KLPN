package com.io.klpn.match_date_edit;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.match.MatchRepository;
import com.io.klpn.match.MatchService;
import com.io.klpn.match_date_edit.dtos.MatchDateEditDTO;
import com.io.klpn.match_date_edit.dtos.MatchDateEditResponseDTO;
import com.io.klpn.reservation.Reservation;
import com.io.klpn.reservation.ReservationRepository;
import com.io.klpn.reservation.ReservationService;
import com.io.klpn.team.TeamRepository;
import com.io.klpn.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchDateEditService {

    final UserRepository userRepository;
    final ReservationRepository reservationRepository;
    final ReservationService reservationService;
    final MatchDateEditRepository matchDateEditRepository;
    final MatchRepository matchRepository;
    final TeamRepository teamRepository;

    public ErrorsListDTO createMatchDateEditRequest(MatchDateEditDTO dto) {
        var errorList = new ErrorsListDTO();
        try {
            isFullHourGiven(dto.newMatchDate());
            isPitchSelected(dto.pitch());
            var user = userRepository.getReferenceById(dto.captainId());
            var reservation = reservationRepository.getReferenceById(dto.reservationId());
            var matchDateEditRequest = new MatchDateEdit(dto.pitch(), dto.newMatchDate().truncatedTo(ChronoUnit.MINUTES), user, reservation);
            matchDateEditRepository.save(matchDateEditRequest);
        }
        catch (IllegalArgumentException exception) {
            errorList.addError(exception.getMessage());
        }

        return errorList;
    }

    public void isFullHourGiven(LocalDateTime dateTime) {
        if(dateTime == null) {
            throw new IllegalArgumentException("Proszę wybrać godzinę!");
        }
        if(dateTime.getMinute() != 0) {
            throw new IllegalArgumentException("Proszę podać pełną godzinę!");
        }
    }

    public void isPitchSelected(Integer pitchNumber) {
        if(pitchNumber == null) {
            throw new IllegalArgumentException("Proszę wybrać numer boiska!");
        }
    }

    public List<MatchDateEditResponseDTO> getAllRequests() {
        var notAcceptedDateChanges = matchDateEditRepository.getAllNotAcceptedRequests();
        return notAcceptedDateChanges.stream()
                .map(mde -> {
                    var captain = mde.getUser();
                    var match = matchRepository.findMatchByReservation_Id(mde.getReservation().getId());
                    var firstTeam = teamRepository.findById(match.getFirstTeamId()).get();
                    var secondTeam = teamRepository.findById(match.getSecondTeamId()).get();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                    var captainName = captain.getFirstName() + ' ' + captain.getLastName();
                    var matchTeams = firstTeam.getName() + " vs " + secondTeam.getName();
                    return new MatchDateEditResponseDTO(mde.getId(), mde.getNewMatchDate().format(formatter),
                            captainName, matchTeams);
                }).toList();
    }

    public MatchDateEdit getMatchDateEditById(Long id) {
        return matchDateEditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wniosek o zmianę daty z podanym id nie istnieje!"));
    }

    public ErrorsListDTO rejectRequest(Long requestId) {
        var errorsList = new ErrorsListDTO();
        try {
            var mde = getMatchDateEditById(requestId);
            matchDateEditRepository.delete(mde);
        }
        catch (IllegalArgumentException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public ErrorsListDTO acceptRequest(Long requestId) {
        var errorsList = new ErrorsListDTO();
        try {
            var mde = getMatchDateEditById(requestId);
            var reservation = reservationService.getReservationById(mde.getReservation().getId());
            mde.setIsAccepted(true);
            reservation.setDate(mde.getNewMatchDate());
            matchDateEditRepository.save(mde);
            reservationRepository.save(reservation);
        }
        catch (IllegalArgumentException exception) {
            errorsList.addError(exception.getMessage());
        }

        return errorsList;
    }
}
