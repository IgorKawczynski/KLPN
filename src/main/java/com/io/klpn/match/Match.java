package com.io.klpn.match;

import com.io.klpn.basic.BasicEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "match")
@NoArgsConstructor
public class Match extends BasicEntity {

    private Long firstTeamId;
    private Long secondTeamId;
    private Long refereeId;
    private Integer firstTeamGoals;
    private Integer secondTeamGoals;

    public Match(Long firstTeamId, Long secondTeamId, Long refereeId, Integer firstTeamGoals, Integer secondTeamGoals) {
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.refereeId = refereeId;
        this.firstTeamGoals = firstTeamGoals;
        this.secondTeamGoals = secondTeamGoals;
    }

}
