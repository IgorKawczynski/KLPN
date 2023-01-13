package com.io.klpn.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsReservationByDateAndPitch(LocalDateTime date, Integer pitch);

    List<Reservation> findReservationsByUser_IdAndDateAfter(Long userId, LocalDateTime date);

    List<Reservation> findReservationsByUser_IdAndDateBefore(Long userId, LocalDateTime date);

    @Query("SELECT r from Reservation r INNER JOIN User u on r.user = u where u.isAdmin = true")
    List<Reservation> getReservationsForAdmin();

    @Query("""
                SELECT r FROM Reservation r
                INNER JOIN User u ON r.user = u
                WHERE u.isAdmin = true
                AND r.date >= :startDate AND r.date < :endDate
            """)
    List<Reservation> getReservationsForAdminAndDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}
