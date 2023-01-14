package com.io.klpn.team;

import com.io.klpn.team.dtos.TeamToAcceptDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Boolean existsByName(String name);

    @Query(value = """
        SELECT new com.io.klpn.team.dtos.TeamToAcceptDTO(t.id, t.name, t.isAccepted)
        FROM Team t WHERE t.isAccepted = false""" )
    List<TeamToAcceptDTO> findTeamsToAccept();

    @Query("SELECT COUNT(s) FROM Student s WHERE s.team = :teamId")
    Long getTeamSize(@Param("teamId") Team team);

    Team findEntityById(Long id);
}
