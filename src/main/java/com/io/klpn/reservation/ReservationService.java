package com.io.klpn.reservation;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.exceptions.AlreadyExistsException;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.reservation.dtos.ReservationRequestDto;
import com.io.klpn.reservation.dtos.ReservationUpdateDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationService {

    final ReservationValidator reservationValidator;

    final ReservationRepository reservationRepository;

    public ErrorsListDTO createReservation (ReservationRequestDto reservationToCreate) {
        var errorsList = new ErrorsListDTO();
        try {
            var reservation = reservationValidator.createReservation(reservationToCreate);
            reservationRepository.save(reservation);
        }
        catch (AlreadyExistsException | NoSuchElementException | IntegerValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with given id does not exists!"));
    }


    public ErrorsListDTO deleteReservationById(Long id) {
        var errorsList = new ErrorsListDTO();
        try {
            reservationValidator.deleteReservationById(id);
        }
        catch (NoSuchElementException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public ErrorsListDTO updateReservation(ReservationUpdateDto reservationToUpdate) {
//        reservationToUpdate: Stare id i user, ale nowa data i boisko
        var errorsList = new ErrorsListDTO();
        var reservationRequestDto = new ReservationRequestDto(reservationToUpdate.userId(), reservationToUpdate.pitch(), reservationToUpdate.date());
        try {
            reservationValidator.validateReservationData(reservationRequestDto);
            var reservation = getReservationById(reservationToUpdate.id());
            reservation.setDate(reservationToUpdate.date());
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
