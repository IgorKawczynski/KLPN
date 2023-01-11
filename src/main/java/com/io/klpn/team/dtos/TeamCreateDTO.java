package com.io.klpn.team.dtos;

import java.util.List;

public record TeamCreateDTO(String name, List<Integer> studentsIndexNumbers) {
}
