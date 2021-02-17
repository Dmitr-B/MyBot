package com.butko.springcourse.botapp.dto;


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

    @JsonCreator
    public Update(Integer updateId, Message message, Message editedMessage) {
        this.updateId = updateId;
        this.message = message;
        this.editedMessage = editedMessage;
    }

    public boolean hasMessage() {
        if (message != null) {
            return true;
        } else return false;
    }

    public boolean hasEditedMessage() {
        if (editedMessage != null) {
            return true;
        } else return false;
    }
}
