package com.io.klpn.reservation;

import com.io.klpn.basic.BasicEntity;
import com.io.klpn.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation extends BasicEntity {

    Integer pitch;
    LocalDateTime date;
    // https://vladmihalcea.com/manytoone-jpa-hibernate/
    // how to properly use ManyToOne annotation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    public Reservation(Integer pitch, LocalDateTime date, User user) {
        this.pitch = pitch;
        this.date = date;
        this.user = user;
    }

}
