package com.io.klpn.user.dtos;

import lombok.Builder;

public record UserToAcceptDTO(Long id,
                              String firstName,
                              String lastName,
                              Integer indexNumber,
                              boolean isAccepted) {

    @Builder
    public UserToAcceptDTO {}
}
