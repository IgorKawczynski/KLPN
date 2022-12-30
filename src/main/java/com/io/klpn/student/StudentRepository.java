package com.io.klpn.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Boolean existsByIndexNumber(Integer indexNumber);

}
