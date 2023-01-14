package com.io.klpn.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

    @Query("""
            SELECT t from Transfer t
            WHERE t.firstStudentId IS NULL
            AND t.secondStudentId IS NOT NULL
            AND t.firstTeamId IS NOT NULL
            AND t.secondTeamId IS NULL
            """)
    List<Transfer> getAllSingleTransfers();

}
