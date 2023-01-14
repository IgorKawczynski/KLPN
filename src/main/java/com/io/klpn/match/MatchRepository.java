package com.io.klpn.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Match findMatchByReservation_Id (Long id);

    List<Match> getMatchesByRefereeId(Long refereeId);

}
