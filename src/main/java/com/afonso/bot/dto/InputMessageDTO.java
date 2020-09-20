package com.afonso.bot.dto;

import org.springframework.stereotype.Component;

@Component
public class InputMessageDTO {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
