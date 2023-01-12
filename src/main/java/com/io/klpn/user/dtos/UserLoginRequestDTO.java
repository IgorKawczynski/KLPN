package com.io.klpn.user.dtos;

import lombok.Builder;

public record UserLoginRequestDTO(String email, String password, Boolean isAdmin) {

    @Builder
    public UserLoginRequestDTO {}
}
