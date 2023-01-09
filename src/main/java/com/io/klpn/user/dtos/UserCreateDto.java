package com.io.klpn.user.dtos;

import lombok.Builder;

public record UserCreateDto(String firstName, String lastName, Integer indexNumber, String email,
                            String password, String phoneNumber) {

    @Builder
    public UserCreateDto {}
}
