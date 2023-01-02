package com.io.klpn.transfer;

import com.io.klpn.basic.BasicEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transfer")
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transfer extends BasicEntity {

    Long firstStudentId;
    Long secondStudentId;
    Long firstTeamId;
    Long secondTeamId;

    public Transfer(Long firstStudentId, Long secondStudentId, Long firstTeamId, Long secondTeamId) {
        this.firstStudentId = firstStudentId;
        this.secondStudentId = secondStudentId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
    }
}
