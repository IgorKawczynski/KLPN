package com.io.klpn.match_date_edit;

import com.io.klpn.basic.BasicEntity;
import com.io.klpn.reservation.Reservation;
import com.io.klpn.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "match_date_edit")
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchDateEdit extends BasicEntity {

    Integer pitch;
    LocalDateTime newMatchDate;
    @OneToOne
    User user; // kapitan zgłaszający potrzebe zmiany terminy
    @OneToOne
    Reservation reservation; // rezerwacja której data ma się zmienić
    Boolean isAccepted; // status tej zmiany odrzucona/zaakceptowana ->

    public MatchDateEdit(Integer pitch, LocalDateTime newMatchDate, User user, Reservation reservation) {
        this.pitch = pitch;
        this.newMatchDate = newMatchDate;
        this.user = user;
        this.reservation = reservation;
        this.isAccepted = false;
    }

}
