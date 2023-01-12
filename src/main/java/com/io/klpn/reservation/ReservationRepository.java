package com.io.klpn.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsReservationByDateAndPitch(LocalDateTime date, Integer pitch);

    List<Reservation> findReservationsByUser_IdAndDateAfter(Long userId, LocalDateTime date);

    List<Reservation> findReservationsByUser_IdAndDateBefore(Long userId, LocalDateTime date);

}
