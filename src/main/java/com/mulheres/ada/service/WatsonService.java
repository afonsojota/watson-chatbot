package com.mulheres.ada.service;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.cloud.sdk.core.service.exception.NotFoundException;
import com.ibm.watson.assistant.v2.Assistant;
import com.ibm.watson.assistant.v2.model.*;
import com.mulheres.ada.dto.InputMessageDTO;
import com.mulheres.ada.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WatsonService {

    @Autowired
    private ResponseDTO responseDTO;

    @Value("${ada.watson.assistantId}")
    private String assistantId;

    @Value("${ada.watson.apiKey}")
    private String apiKey;

    @Value("${ada.watson.versionDate}")
    private String versionDate;

    @Value("${ada.watson.url}")
    private String watsonUrl;

    public SessionResponse getSession(){
        Assistant assistant = authenticateAndGetAssistant();

        CreateSessionOptions options = new CreateSessionOptions.Builder(assistantId).build();

        SessionResponse response = assistant.createSession(options).execute().getResult();

        return response;
    }

    private Assistant authenticateAndGetAssistant(){
        IamAuthenticator authenticator = new IamAuthenticator(apiKey);
        Assistant assistant = new Assistant(versionDate, authenticator);
        assistant.setServiceUrl(watsonUrl);

        return assistant;
    }

    public ResponseDTO getMessage(String session, InputMessageDTO inputMessage){
        Assistant assistant = authenticateAndGetAssistant();

        MessageOptions options = getMessageOptions(inputMessage.getMessage(), session);

        MessageOutput output = null;
        try{
            output = assistant.message(options).execute().getResult().getOutput();
        }catch (NotFoundException exception){
            session = getSession().getSessionId();
            options = getMessageOptions(inputMessage.getMessage(), session);
            output = assistant.message(options).execute().getResult().getOutput();
        }

        String code = output.getIntents().size() > 0 ? "#" + output.getIntents().get(0).intent() : "#none";
        String message = output.getGeneric().get(0).text();

        message = message != null ? message : "Desculpe, não entendi.";

        responseDTO.setSession(session);
        responseDTO.setMessage(message);
        responseDTO.setCode(code);

        System.out.println(output);

        return responseDTO;
    }

    private MessageOptions getMessageOptions(String message, String session){
        MessageInput input = new MessageInput.Builder()
                .messageType("text")
                .text(message)
                .build();
        return new MessageOptions.Builder(assistantId, session)
                .input(input)
                .build();
    }

}
