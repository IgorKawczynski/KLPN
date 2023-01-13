package com.io.klpn.match;

import com.io.klpn.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

//    @Query(value = "SELECT r.date FROM public.reservation r INNER JOIN public.user u ON r.user_id = u.id WHERE u.is_admin=true", nativeQuery = true)
//    List<Object> getDatesReservedForAdmin();

    @Query("SELECT r from Reservation r INNER JOIN User u on r.user = u where u.isAdmin = true")
    List<Reservation> getDatesReservedForAdmin();

}
