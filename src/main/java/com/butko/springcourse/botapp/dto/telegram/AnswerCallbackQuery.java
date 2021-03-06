package com.butko.springcourse.botapp.dto.telegram;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AnswerCallbackQuery {

    @JsonProperty("callback_query_id")
    private String callbackQueryId;

    @JsonProperty("text")
    private String text;

    @JsonCreator
    public AnswerCallbackQuery(@JsonProperty("callback_query_id")String callbackQueryId,
                               @JsonProperty("text")String text) {
        this.callbackQueryId = callbackQueryId;
        this.text = text;
    }

    @Override
    public String toString() {
        return "AnswerCallbackQuery{" +
                "text='" + text + '\'' +
                '}';
    }
}
