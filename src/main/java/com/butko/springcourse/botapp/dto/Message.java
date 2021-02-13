package com.butko.springcourse.botapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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

}
