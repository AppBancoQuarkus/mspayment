package com.nttd.billeteradig.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {

    private String aplicacion;
    private String aplicacion1;
    
    public UserRequest(String aplicacion) {
        this.aplicacion = aplicacion;
    }


    
    
}
