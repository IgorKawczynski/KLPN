package com.io.klpn.reservation;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.reservation.dtos.ReservationRequestDto;
import com.io.klpn.reservation.dtos.ReservationResponseDTO;
import com.io.klpn.reservation.dtos.ReservationUpdateDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationService {

    final ReservationValidator reservationValidator;

    final ReservationRepository reservationRepository;

    public ErrorsListDTO createReservation (ReservationRequestDto reservationToCreate) {
        var errorsList = new ErrorsListDTO();
        var reservationToCreateWithoutSeconds = new ReservationRequestDto(reservationToCreate.userId(),
                reservationToCreate.pitch(), reservationToCreate.date().truncatedTo(ChronoUnit.MINUTES));
        try {
            var reservation = reservationValidator.createReservation(reservationToCreateWithoutSeconds);
            reservationRepository.save(reservation);
        }
        catch (AlreadyExistsException | NoSuchElementException | IntegerValidatorException | IllegalArgumentException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rezerwacja z podanym id nie istnieje!"));
    }

    public ReservationResponseDTO getReservationResponseById(Long id) {
        var reservation = reservationRepository.findById(id).get();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        return new ReservationResponseDTO(reservation.getUser().getId(), reservation.getPitch(), formatter.format(reservation.getDate()), reservation.getId());
    }

    public List<ReservationResponseDTO> getReservationsByUserIdAndDateAfterNow(Long userId) {
        LocalDateTime actualDayAndHour = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<Reservation> reservationList =  reservationRepository.findReservationsByUser_IdAndDateAfter(userId, actualDayAndHour);

        List<ReservationResponseDTO> reservationResponseDTOS = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        for (Reservation reservation: reservationList) {
            var reservationToAdd = new ReservationResponseDTO(reservation.getUser().getId(), reservation.getPitch(), formatter.format(reservation.getDate()), reservation.getId());
            reservationResponseDTOS.add(reservationToAdd);
        }
        return reservationResponseDTOS;
    }

    public List<ReservationResponseDTO> getReservationsByUserIdAndDateBeforeNow(Long userId) {
        LocalDateTime actualDayAndHour = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<Reservation> reservationList =  reservationRepository.findReservationsByUser_IdAndDateBefore(userId, actualDayAndHour);

        List<ReservationResponseDTO> reservationResponseDTOS = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        for (Reservation reservation: reservationList) {
            var reservationToAdd = new ReservationResponseDTO(reservation.getUser().getId(), reservation.getPitch(), formatter.format(reservation.getDate()), reservation.getId());
            reservationResponseDTOS.add(reservationToAdd);
        }
        return reservationResponseDTOS;
    }

    public ErrorsListDTO deleteReservationById(Long id) {
        var errorsList = new ErrorsListDTO();
        try {
            reservationValidator.deleteReservationById(id);
        }
        catch (NoSuchElementException | IllegalArgumentException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public ErrorsListDTO updateReservation(ReservationUpdateDto reservationToUpdate) {
//        reservationToUpdate: Stare id i userId, ale nowa data i boisko
        var errorsList = new ErrorsListDTO();

        var reservationRequestDto = new ReservationRequestDto(reservationToUpdate.userId(), reservationToUpdate.pitch(), reservationToUpdate.date().truncatedTo(ChronoUnit.MINUTES));
        var updatedReservation = getReservationById(reservationToUpdate.id());

        try {
            if (updatedReservation.getDate().isBefore(LocalDateTime.now()) )
                throw new IllegalArgumentException("Nie można edytować rezerwacji, która się odbyła!");
            if (updatedReservation.getDate().isBefore(LocalDateTime.now().plusHours(3)))
                throw new IllegalArgumentException("Rezerwację można edytować z co najmniej 3 godzinnym wyprzedzeniem!");

            reservationValidator.validateReservationData(reservationRequestDto);

            var reservation = getReservationById(reservationToUpdate.id());
            reservation.setDate(reservationToUpdate.date().truncatedTo(ChronoUnit.MINUTES));
            reservation.setPitch(reservationToUpdate.pitch());
            reservationRepository.save(reservation);
        }
        catch (AlreadyExistsException | NoSuchElementException | IntegerValidatorException
                | IllegalArgumentException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

}
