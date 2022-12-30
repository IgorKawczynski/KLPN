package com.io.klpn.user.dtos;

import lombok.Builder;

public record UserResponseDto(Long id, String firstName, String lastName, String phoneNumber) {

    @Builder
    public UserResponseDto {}
}
