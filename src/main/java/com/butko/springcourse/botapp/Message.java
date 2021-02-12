package com.butko.springcourse.botapp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

public class Message {
    @JsonProperty("message_id")
    private Integer message_id;

    @JsonProperty("chat")
    private Chat chat;

    @JsonProperty("text")
    private String text;

    @JsonCreator
    public Message(Integer message_id, Chat chat, String text) {
        this.message_id = message_id;
        this.chat = chat;
        this.text = text;
    }

    
    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
