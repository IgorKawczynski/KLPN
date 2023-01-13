package com.io.klpn.match;

import com.io.klpn.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("SELECT r from Reservation r INNER JOIN User u on r.user = u where u.isAdmin = true")
    List<Reservation> getReservationsForAdmin();

}
