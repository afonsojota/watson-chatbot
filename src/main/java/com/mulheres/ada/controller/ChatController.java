package com.mulheres.ada.controller;

import com.ibm.watson.assistant.v2.model.*;
import com.mulheres.ada.dto.InputMessageDTO;
import com.mulheres.ada.dto.ResponseDTO;
import com.mulheres.ada.service.WatsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class ChatController {

    @Autowired
    private WatsonService watsonService;

    @GetMapping("/session")
    public SessionResponse getSession(){
        return watsonService.getSession();
    }

    @GetMapping("/message")
    public ResponseDTO botAssistant(@PathParam("session") String session, @RequestBody InputMessageDTO inputMessage){
        return watsonService.getMessage(session, inputMessage);
    }

}
