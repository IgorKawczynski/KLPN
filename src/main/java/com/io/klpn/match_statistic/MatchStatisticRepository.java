package com.io.klpn.match_statistic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchStatisticRepository extends JpaRepository<MatchStatistic, Long> {
}
