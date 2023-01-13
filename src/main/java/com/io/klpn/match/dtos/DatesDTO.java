package com.io.klpn.match.dtos;

import lombok.Builder;

import java.util.List;

public record DatesDTO(List<String> dates) {

    @Builder
    public DatesDTO {}
}
