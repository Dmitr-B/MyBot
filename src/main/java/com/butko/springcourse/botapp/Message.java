package com.butko.springcourse.botapp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

public class Message {
    @JsonProperty("message_id")
    private Integer message_id;

    @JsonCreator
    public Message(Integer message_id) {
        this.message_id = message_id;
    }

    
    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }
}
