package com.io.klpn.user.dtos;

import lombok.Builder;

public record UserEditDTO(Long id,
                          String firstName,
                          String lastName,
                          String email,
                          String phoneNumber) {

    @Builder
    public UserEditDTO {}
}
