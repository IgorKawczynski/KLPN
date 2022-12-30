package com.io.klpn.match_statistic;

import com.io.klpn.basic.BasicEntity;
import com.io.klpn.match.Match;
import com.io.klpn.match_statistic.enums.Event;
import com.io.klpn.student.Student;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "match_statistic")
@NoArgsConstructor
public class MatchStatistic extends BasicEntity {

    @Enumerated(EnumType.STRING)
    private Event event;
    private Integer minute;
    @OneToOne
    @JoinColumn(name = "player_id")
    private Student student;
    @OneToOne
    private Match match;

    // we can use getReferenceById method from JpaRepository to obtain student and match
    public MatchStatistic(Event event, Integer minute, Student student, Match match) {
        this.event = event;
        this.minute = minute;
        this.student = student;
        this.match = match;
    }

}
