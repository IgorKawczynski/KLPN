package com.io.klpn.match;

import com.io.klpn.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    Match findMatchByReservation_Id (Long id);

    List<Match> getMatchesByRefereeId(Long refereeId);

    @Query("SELECT m FROM Match m WHERE m.firstTeamId = :teamId OR m.secondTeamId = :teamId")
    List<Match> getMatchesForTeam(@Param("teamId") Long teamId);

}
