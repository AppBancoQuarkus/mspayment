package com.nttd.billeteradig.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    
    private int code;
    private String message;
    private String errorMessage;
    private String description;
    private Object response;

    public UserResponse() {
    }

    public UserResponse(int code, String message, Object response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public UserResponse(int code, String errorMessage, String description) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.description = description;
    }

}
