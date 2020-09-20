package com.afonso.bot.dto;

import com.ibm.watson.assistant.v2.model.RuntimeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseDTO {

    private String session;
    private String message;
    private String intent;
    private List<Entity> entities = new ArrayList<>();

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

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

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<Entity> parseEntity(List<RuntimeEntity> watsonEntities) {
        List<Entity> result = new ArrayList<>();

        if(watsonEntities != null){
            for (RuntimeEntity item : watsonEntities){
                Entity entity = new Entity();
                entity.setEntity(item.entity());
                entity.setValue(item.value());
                result.add(entity);
            }
        }

        return result;
    }
}