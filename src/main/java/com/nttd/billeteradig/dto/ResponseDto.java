package com.nttd.billeteradig.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ResponseDto {
    
    private int code;
    private String message;
    private String errorMessage;
    private String description;
    private Object response;

    public ResponseDto() {
    }

    public ResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseDto(int code, String message, Object response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public ResponseDto(int code, String errorMessage, String description) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.description = description;
    }

}
