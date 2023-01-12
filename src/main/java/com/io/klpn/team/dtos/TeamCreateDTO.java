package com.io.klpn.team.dtos;

import com.io.klpn.student.StudentPlayerDTO;

import java.util.List;
import java.util.stream.Collectors;

public record TeamCreateDTO(String name, List<StudentPlayerDTO> players, Long captainId) {

    public List<Integer> indexes() {
        return this.players()
                .stream()
                .mapToInt(StudentPlayerDTO::indexNumber)
                .boxed()
                .toList();
    }

    public List<Boolean> referees() {
        return this.players
                .stream()
                .map(StudentPlayerDTO::isReferee)
                .collect(Collectors.toList());
    }

}
