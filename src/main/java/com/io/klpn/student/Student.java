package com.io.klpn.student;

import com.io.klpn.student.enums.Position;
import com.io.klpn.student.enums.Role;
import com.io.klpn.team.Team;
import com.io.klpn.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class Student {

    @Id
    @Column(name = "id")
    Long id;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    User user;

    Integer indexNumber;
    @Enumerated(EnumType.STRING)
    Role role;
    @Enumerated(EnumType.STRING)
    Position position;
    Integer tshirtNumber;
    Boolean isAccepted;
    Integer motmAmount;
    @OneToOne
    Team team;

    public Student(Long id, Integer indexNumber) {
        this.id = id;
        this.indexNumber = indexNumber;
        this.isAccepted = false;
        this.motmAmount = 0;
    }

    public void increaseMotmAmount() {
        this.motmAmount = this.motmAmount + 1;
    }

}
