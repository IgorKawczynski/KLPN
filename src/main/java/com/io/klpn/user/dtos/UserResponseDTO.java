package com.io.klpn.user.dtos;

import lombok.Builder;

public record UserResponseDTO(Long id, String firstName, String lastName, String phoneNumber) {

    @Builder
    public UserResponseDTO {}
}
