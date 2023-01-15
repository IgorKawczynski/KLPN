package com.io.klpn.match;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.match.dtos.MatchForStudentResponseDTO;
import com.io.klpn.match.dtos.MatchResponseDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/match")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchController {

    final MatchService matchService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Page<Match> getAllMatches(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return matchService.getAllMatches(page);
    }

    @GetMapping("/dates")
    public List<LocalDate> getDatesReservedForAdmin() {
        return matchService.getDatesReservedForAdmin();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Match getMatchById(@PathVariable Long id){
        return matchService.getMatchById(id);
    }

    @GetMapping("/for-referee/{id}")
    public MatchForStudentResponseDTO getMatchForRefereeById(@PathVariable Long id){
        return matchService.getMatchForRefereeByMatchId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorsListDTO createMatch(@RequestBody Match match) {
        return matchService.createMatch(match);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorsListDTO deleteMatchById(@PathVariable Long id) {
        return matchService.deleteMatchById(id);
    }

    @PostMapping("/for-day")
    public List<MatchResponseDTO> getMatchesListByDay(@RequestBody LocalDateTime date) {
        return matchService.getMatchResponseDTOsListByDay(date);
    }

    @PostMapping("/referee-matches")
    public List<MatchForStudentResponseDTO> getMatchesForReferee(@RequestBody Long refereeId) {
        return matchService.getMatchesForRefereeByRefereeId(refereeId);
    }

    @GetMapping("/student-matches/{studentId}")
    public List<MatchForStudentResponseDTO> getMatchesForStudent(@PathVariable Long studentId) {
        return matchService.getMatchesForStudentTeam(studentId);
    }

    @GetMapping("/to-edit/{reservationId}")
    public MatchForStudentResponseDTO getMatchByReservationId(@PathVariable Long reservationId) {
        return matchService.getMatchForStudentByReservationId(reservationId);
    }

}
