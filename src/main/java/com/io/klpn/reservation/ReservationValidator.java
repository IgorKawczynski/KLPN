package com.io.klpn.reservation;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.reservation.dtos.ReservationRequestDto;
import com.io.klpn.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

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
        validatorService.validateIntegerBiggerThan(reservation.pitch(), ValidatorService.MAX_PITCH_NUMBER);
        validatorService.validateIntegerLessThan(reservation.pitch(), ValidatorService.MIN_PITCH_NUMBER);
        isReservationAvailable(reservation);
    }

    public void isReservationAvailable(ReservationRequestDto reservation){
       if (reservationRepository.existsReservationByDateAndPitch(reservation.date(), reservation.pitch())) {
           throw new AlreadyExistsException(
                   String.format("Reservation for given data: %s and pitch number: %d already exists!",
                           reservation.date().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")), reservation.pitch()));
       }
    }

    public void deleteReservationById(Long id) {
        if(!reservationRepository.existsById(id))
            throw new NoSuchElementException(String.format("Reservation with id: %d does not exist. ", id));
        reservationRepository.deleteById(id);
    }
}
