package com.io.klpn.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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

    @Transactional // potrzebna adnotacja do updatea, inaczej leci error
    @Modifying // potrzebna adnotacja do update'a, inaczej leci error
    @Query("UPDATE Student s SET s.role = 'REFEREE' WHERE s.indexNumber = :indexNumber")
    void updateToReferee(@Param("indexNumber") Integer indexNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.role = 'PLAYER' WHERE s.indexNumber = :indexNumber")
    void updateToPlayer(@Param("indexNumber") Integer indexNumber);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.role = 'CAPTAIN' WHERE s.indexNumber = :indexNumber")
    void updateToCaptain(@Param("indexNumber") Integer indexNumber);

}
