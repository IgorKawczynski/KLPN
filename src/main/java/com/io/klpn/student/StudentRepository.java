package com.io.klpn.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Boolean existsByIndexNumber(Integer indexNumber);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s where s.indexNumber = :indexNumber and isAccepted = true")
    Boolean checkIfStudentIsAccepted(@Param("indexNumber") Integer indexNumber);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s where s.indexNumber = :indexNumber and team != null")
    Boolean checkIfStudentIsAssignedToAnotherTeam(@Param("indexNumber") Integer indexNumber);

    List<Student> findAllByIndexNumberIn(List<Integer> indexes);

}
