package com.io.klpn.transfer;

import com.io.klpn.basic.BasicEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "transfer")
@NoArgsConstructor
public class Transfer extends BasicEntity {

    private Long firstStudentId;
    private Long secondStudentId;
    private Long firstTeamId;
    private Long secondTeamId;

    public Transfer(Long firstStudentId, Long secondStudentId, Long firstTeamId, Long secondTeamId) {
        this.firstStudentId = firstStudentId;
        this.secondStudentId = secondStudentId;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
    }

}
