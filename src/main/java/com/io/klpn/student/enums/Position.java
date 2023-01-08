package com.io.klpn.student.enums;

import java.util.Objects;
import java.util.stream.Stream;

public enum Position {

    GOALKEEPER("GK"),
    DEFENDER("DF"),
    MIDFIELDER("MD"),
    STRIKER("ST");

    private String positionType;

    Position(String positionType) {
        this.positionType = positionType;
    }

    public String getPositionType() {
        return this.positionType;
    }

    public static Position getPosition(String positionType) {
        return Stream.of(values())
                .filter(position -> position.positionType.equals(positionType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("positionType must be one of '%s', '%s', '%s' or '%s'",
                        GOALKEEPER.getPositionType(), DEFENDER.getPositionType(), MIDFIELDER.getPositionType(), STRIKER.getPositionType())));
    }

    public boolean isGoalkeeper() {
        return Objects.equals(this.positionType, GOALKEEPER.getPositionType());
    }

    public boolean isDefender() {
        return Objects.equals(this.positionType, DEFENDER.getPositionType());
    }

    public boolean isMidfielder() {
        return Objects.equals(this.positionType, MIDFIELDER.getPositionType());
    }

    public boolean isStriker() {
        return Objects.equals(this.positionType, STRIKER.getPositionType());
    }

}
