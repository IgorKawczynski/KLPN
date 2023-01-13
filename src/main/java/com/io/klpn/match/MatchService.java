package com.io.klpn.match;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.match.dtos.DatesDTO;
import com.io.klpn.reservation.Reservation;
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
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchService {

    final MatchRepository matchRepository;
    final MatchValidator matchValidator;

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

//    public List<LocalDate> getDatesReservedForAdmin(){
//        List<Object> datesAsObjects = matchRepository.getDatesReservedForAdmin();
//        List<LocalDate> datesAsDays = new ArrayList<>();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//
//        for (Object date: datesAsObjects) {
//            var dateToAdd = date.toString().substring(0, 10);
//            var formatedDate = LocalDate.parse(formatter.format(LocalDate.parse(dateToAdd)));
//            datesAsDays.add(formatedDate);
//        }
//
//        TreeSet<LocalDate> uniqueDatesSorted = new TreeSet<>(datesAsDays);
//
//        return uniqueDatesSorted.stream().toList();
//    }

    public List<LocalDateTime> getDatesReservedForAdmin(){
        List<Reservation> reservationList = matchRepository.getDatesReservedForAdmin();
        List<LocalDate> datesAsDays = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        List<LocalDateTime> reservationDates = reservationList.stream().map(Reservation::getDate).distinct().toList();

        return reservationDates;
    }

}
