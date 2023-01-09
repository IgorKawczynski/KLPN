package com.io.klpn.user.dtos;

import lombok.Builder;

public record UserRegisterDTO(String firstName, String lastName, Integer indexNumber, String email,
                              String password, String phoneNumber) {

    @Builder
    public UserRegisterDTO {}
}
