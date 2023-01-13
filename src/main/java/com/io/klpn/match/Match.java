package com.io.klpn.match;

import com.io.klpn.basic.BasicEntity;
import com.io.klpn.reservation.Reservation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match")
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Match extends BasicEntity {

    Long firstTeamId;
    Long secondTeamId;
    Long refereeId;
    Integer firstTeamGoals;
    Integer secondTeamGoals;
    @OneToOne
    Reservation reservation;


    public Match(Long firstTeamId, Long secondTeamId, Long refereeId, Integer firstTeamGoals, Integer secondTeamGoals, Reservation reservation) {
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.refereeId = refereeId;
        this.firstTeamGoals = firstTeamGoals;
        this.secondTeamGoals = secondTeamGoals;
        this.reservation = reservation;
    }

}
