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

//    @JsonProperty("reply_markup")
//    private ReplyKeyboardMarkup replyMarkup;

    @JsonCreator
    public SendMessage(@JsonProperty("chat_id") Integer chatId,
                       @JsonProperty("text") String text/*,
                       @JsonProperty("reply_markup") ReplyKeyboardMarkup replyMarkup*/) {
        this.chatId = chatId;
        this.text = text;
        //this.replyMarkup = replyMarkup;
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "text='" + text + '\'' +
                '}';
    }
}
