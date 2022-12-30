package com.io.klpn.team;

import com.io.klpn.basic.BasicEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "team")
@NoArgsConstructor
public class Team extends BasicEntity {

    private String name;
    private Integer numberOfMatches;
    private Integer wins;
    private Integer draws;
    private Integer loses;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer balance;
    private Integer points;

    public Team(String name) {
        this.name = name;
    }

}
