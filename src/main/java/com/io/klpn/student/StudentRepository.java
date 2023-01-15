package com.io.klpn.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Boolean existsByIndexNumber(Integer indexNumber);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s where s.indexNumber = :indexNumber and isAccepted = true")
    Boolean checkIfStudentIsAccepted(@Param("indexNumber") Integer indexNumber);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s where s.id = :id and isAccepted = true")
    Boolean checkIfStudentIsAcceptedById(@Param("id") Long id);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Student s where s.indexNumber = :indexNumber and team != null")
    Boolean checkIfStudentIsAssignedToAnotherTeam(@Param("indexNumber") Integer indexNumber);

    List<Student> findAllByIndexNumberIn(List<Integer> indexes);

    Student findEntityById(Long id);

    List<Student> findAllByTeam_Id(Long id);

    Optional<Long> findTeamById(Long id);

    @Query("SELECT s FROM Student s WHERE s.indexNumber = :indexNumber")
    Student getStudentByIndexNumber(@Param("indexNumber") Integer indexNumber);

    Student findStudentByIndexNumber(Integer studentIndex);
}
