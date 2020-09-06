package com.mulheres.ada.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseDTO {

    private String session;
    private String message;
    private String code;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
