package com.io.klpn.match_statistic;

import com.io.klpn.match_statistic.dtos.MatchStatisticCountDTO;
import com.io.klpn.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchStatisticRepository extends JpaRepository<MatchStatistic, Long> {


    @Query("""
            select new com.io.klpn.match_statistic.dtos.MatchStatisticCountDTO(count(s) as quantity, s.event as event)
            from MatchStatistic s where student = :userId group by s.event
           """)
    List<MatchStatisticCountDTO> findAllMatchStatisticsByUserId(@Param("userId") Student student);

    List<MatchStatistic> getMatchStatisticsByMatch_Id(Long matchId);
}
