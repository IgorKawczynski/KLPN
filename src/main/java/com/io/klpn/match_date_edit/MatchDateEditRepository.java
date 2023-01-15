package com.io.klpn.match_date_edit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDateEditRepository extends JpaRepository<MatchDateEdit, Long> {
}
