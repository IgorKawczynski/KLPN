package com.io.klpn.student;

import com.io.klpn.student.enums.Position;

public record StudentPlayerDTO(Integer indexNumber, Position position, Boolean isReferee) {
}
