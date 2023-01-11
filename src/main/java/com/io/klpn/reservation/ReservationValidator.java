package com.io.klpn.reservation;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.reservation.dtos.ReservationRequestDto;
import com.io.klpn.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationValidator {

    final ValidatorService validatorService;

    final UserRepository userRepository;

    final ReservationRepository reservationRepository;

    public Reservation createReservation(ReservationRequestDto reservation) {
        validateReservationData(reservation);
        var user = userRepository.getReferenceById(reservation.userId());
        return new Reservation(reservation.pitch(), reservation.date(), user);
    }

    public void validateReservationData(ReservationRequestDto reservation) {
        validatorService.isNull("data", reservation.date());
        validatorService.validateIntegerBiggerThan(reservation.pitch(), ValidatorService.MAX_PITCH_NUMBER);
        validatorService.validateIntegerLessThan(reservation.pitch(), ValidatorService.MIN_PITCH_NUMBER);
        if (reservation.date().getMinute() != 0)
            throw new IllegalArgumentException("Wprowadź pełną godzinę!");
        isReservationAvailable(reservation);
    }

    public void isReservationAvailable(ReservationRequestDto reservation){
        if (reservation.date().isBefore(LocalDateTime.now()) )
            throw new IllegalArgumentException("Wybrana data nie może być datą przeszłą!");
        if (reservationRepository.existsReservationByDateAndPitch(reservation.date(), reservation.pitch())) {
           throw new AlreadyExistsException(
                   String.format("Rezerwacja dla podanej daty: %s i numeru boiska: %d już istnieje!",
                           reservation.date().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")), reservation.pitch()));
       }
    }

    public void deleteReservationById(Long id) {
        if(!reservationRepository.existsById(id))
            throw new NoSuchElementException(String.format("Rezerwacja o podanym id: %d nie istnieje. ", id));
        var reservationToDelete = reservationRepository.getReferenceById(id);
        if(reservationToDelete.getDate().isBefore(LocalDateTime.now()))
            throw new IllegalArgumentException("Nie można usunąć rezerwacji z przeszłości!");
        if(reservationToDelete.getDate().isBefore(LocalDateTime.now().plusHours(3)))
            throw new IllegalArgumentException("Rezerwację można usunąć z co najmniej 3 godzinnym wyprzedzeniem!");
        reservationRepository.deleteById(id);
    }
}
