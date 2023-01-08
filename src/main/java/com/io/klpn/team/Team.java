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
    Boolean isAccepted;

    public Team(String name) {
        this.name = name;
        this.numberOfMatches = 0;
        this.wins = 0;
        this.draws = 0;
        this.loses = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.balance = 0;
        this.points = 0;
        this.isAccepted = false;
    }
    public Team() {}
}
