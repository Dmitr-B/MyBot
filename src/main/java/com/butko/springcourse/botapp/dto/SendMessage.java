package com.butko.springcourse.botapp.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessage {

    @JsonProperty("chat_id")
    private Integer chatId;
    private String text;

    @JsonCreator
    public SendMessage(@JsonProperty("chat_id") Integer chatId,
                       @JsonProperty("text") String text) {
        this.chatId = chatId;
        this.text = text;
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "text='" + text + '\'' +
                '}';
    }
}
