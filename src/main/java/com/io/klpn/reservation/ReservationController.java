package com.io.klpn.reservation;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.reservation.dtos.ReservationRequestDto;
import com.io.klpn.reservation.dtos.ReservationUpdateDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationController {

    final ReservationService reservationService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorsListDTO createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        return reservationService.createReservation(reservationRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorsListDTO deleteReservationById(@PathVariable Long id) {
        return reservationService.deleteReservationById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Reservation getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PatchMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ErrorsListDTO updateReservation(@RequestBody ReservationUpdateDto reservationToUpdate) {
        return reservationService.updateReservation(reservationToUpdate);
    }
}
