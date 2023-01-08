package com.io.klpn.student.enums;

import java.util.Objects;
import java.util.stream.Stream;

public enum Role {

    PLAYER("player"),
    CAPTAIN("captain"),
    REFEREE("referee");

    private final String roleType;

    Role(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return this.roleType;
    }

    public static Role getRole(String roleType) {
        return Stream.of(values())
                .filter(role -> role.roleType.equals(roleType))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("roleType must be one of '%s', '%s' or '%s'",
                        PLAYER.getRoleType(), CAPTAIN.getRoleType(), REFEREE.getRoleType())));
    }

    public boolean isPlayer() {
        return Objects.equals(this.roleType, PLAYER.getRoleType());
    }

    public boolean isCaptain() {
        return Objects.equals(this.roleType, CAPTAIN.getRoleType());
    }

    public boolean isReferee() {
        return Objects.equals(this.roleType, REFEREE.getRoleType());
    }

}
