package com.nttd.billeteradig.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponse {
    
    private String name;
    private String lastname;
    private String telephone;
    private String email;
    private String password;
    private long idAccount;
    private String state;


}
