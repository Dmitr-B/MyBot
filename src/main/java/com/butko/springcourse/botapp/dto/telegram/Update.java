package com.butko.springcourse.botapp.dto.telegram;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Update {
    @JsonProperty("update_id")
    private Integer updateId;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("edited_message")
    private Message editedMessage;

    @JsonProperty("callback_query")
    private CallbackQuery callbackQuery;

    @JsonCreator
    public Update(Integer updateId, Message message, Message editedMessage, CallbackQuery callbackQuery) {
        this.updateId = updateId;
        this.message = message;
        this.editedMessage = editedMessage;
        this.callbackQuery = callbackQuery;
    }

    public boolean hasMessage() {
        return message != null;
    }

    public boolean hasEditedMessage() {
        return editedMessage != null;
    }

    public boolean hasCallbackQuery() {
        return callbackQuery != null;
    }
}
