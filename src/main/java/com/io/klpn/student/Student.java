package com.io.klpn.student;

import com.io.klpn.student.enums.Position;
import com.io.klpn.student.enums.Role;
import com.io.klpn.team.Team;
import com.io.klpn.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Student extends User {

    private Integer indexNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Position position;
    private Integer tshirtNumber;
    private Boolean isAccepted;
    private Integer motmAmount;
    @OneToOne
    private Team team;

    public Student(Long id, Integer indexNumber) {
        super(id);
        this.indexNumber = indexNumber;
        this.isAccepted = false;
    }

    public Student(Long id, Integer indexNumber, Role role, Position position) {
        super(id);
        this.indexNumber = indexNumber;
        this.role = role;
        this.position = position;
        this.isAccepted = false;
    }

}
