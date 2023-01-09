package com.io.klpn.basic;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ErrorsListDTO implements Serializable {

    private List<String> errors = new ArrayList<>();

    private String fieldName; //nazwy pola pod ktorymi wyswietlane beda errory

    public boolean isListOfErrorsEmpty(){
        return this.errors.size()==0;
    }

    public void addError(String error){
        this.errors.add(error);
    }

    public void addError(ErrorsListDTO errorsListDTO) {
        errorsListDTO.errors
                .forEach(this::addError);
    }

}
