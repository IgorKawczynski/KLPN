package com.io.klpn.user.dtos;

import lombok.Builder;

public record UserLoginRequestDTO(Long id, String email, String password, Boolean isAdmin) {

    @Builder
    public UserLoginRequestDTO {}
}
