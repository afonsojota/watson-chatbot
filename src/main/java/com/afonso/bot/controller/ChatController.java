package com.afonso.bot.controller;

import com.ibm.watson.assistant.v2.model.*;
import com.afonso.bot.dto.InputMessageDTO;
import com.afonso.bot.dto.ResponseDTO;
import com.afonso.bot.service.WatsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class ChatController {

    @Autowired
    private WatsonService watsonService;

    @GetMapping("/session")
    public SessionResponse getSession(){
        return watsonService.getSession();
    }

    @PostMapping("/message")
    public ResponseDTO botAssistant(@PathParam("session") String session, @RequestBody InputMessageDTO inputMessage){
        return watsonService.getMessage(session, inputMessage);
    }

}
