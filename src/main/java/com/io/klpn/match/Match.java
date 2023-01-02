package com.io.klpn.match;

import com.io.klpn.basic.BasicEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "match")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Match extends BasicEntity {

    Long firstTeamId;
    Long secondTeamId;
    Long refereeId;
    Integer firstTeamGoals;
    Integer secondTeamGoals;

    public Match(Long firstTeamId, Long secondTeamId, Long refereeId, Integer firstTeamGoals, Integer secondTeamGoals) {
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.refereeId = refereeId;
        this.firstTeamGoals = firstTeamGoals;
        this.secondTeamGoals = secondTeamGoals;
    }

}
