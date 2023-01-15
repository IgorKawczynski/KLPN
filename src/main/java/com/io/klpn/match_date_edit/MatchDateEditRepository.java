package com.io.klpn.match_date_edit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchDateEditRepository extends JpaRepository<MatchDateEdit, Long> {

    @Query("SELECT mde FROM MatchDateEdit mde WHERE mde.isAccepted = false")
    List<MatchDateEdit> getAllNotAcceptedRequests();

}
