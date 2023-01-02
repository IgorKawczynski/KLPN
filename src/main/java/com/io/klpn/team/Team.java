package com.io.klpn.team;

import com.io.klpn.basic.BasicEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "team")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Team extends BasicEntity {

    String name;
    Integer numberOfMatches;
    Integer wins;
    Integer draws;
    Integer loses;
    Integer goalsFor;
    Integer goalsAgainst;
    Integer balance;
    Integer points;

    public Team(String name) {
        this.name = name;
    }
    public Team() {}
}
