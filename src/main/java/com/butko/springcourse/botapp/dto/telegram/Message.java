package com.butko.springcourse.botapp.dto.telegram;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {
    @JsonProperty("message_id")
    private Integer messageId;

    @JsonProperty("chat")
    private Chat chat;

    @JsonProperty("text")
    private String text;

    @JsonProperty("contact")
    private Contact contact;

    @JsonProperty("from")
    private User from;

    @JsonCreator
    public Message(Integer messageId, Chat chat, String text, Contact contact, User from) {
        this.messageId = messageId;
        this.chat = chat;
        this.text = text;
        this.contact = contact;
        this.from = from;
    }
}
