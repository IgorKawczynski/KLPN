package com.io.klpn.user.dtos;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.student.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginResponseDTO implements Serializable {

    private String sessionId;

    private Long id;

    private Boolean isAdmin;

    private Boolean isStudent;
    private Role role;
    private String name;
    private ErrorsListDTO errorsListDTO;

    public UserLoginResponseDTO(ErrorsListDTO errorsListDTO) {
        this.errorsListDTO = errorsListDTO;
    }
    public void addToErrorList(String error) {
        this.errorsListDTO.addError(error);
    }
}
