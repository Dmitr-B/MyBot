package com.butko.springcourse.botapp.dto.telegram;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class SendMessage {

    @JsonProperty("chat_id")
    private Integer chatId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("reply_markup")
    private Keyboard replyMarkup;

    @JsonCreator
    public SendMessage(@JsonProperty("chat_id") Integer chatId,
                       @JsonProperty("text") String text,
                       @JsonProperty("reply_markup") Keyboard replyMarkup) {
        this.chatId = chatId;
        this.text = text;
        this.replyMarkup = replyMarkup;
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "chatId=" + chatId +
                ", text='" + text + '\'' +
                ", replyMarkup=" + replyMarkup +
                '}';
    }
}
