package com.butko.springcourse.botapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Chat {

    @JsonProperty("id")
    private Integer chat_id;

    @JsonCreator
    public Chat(Integer chat_id) {
        this.chat_id = chat_id;
    }

    public Integer getChat_id() {
        return chat_id;
    }

    public void setChat_id(Integer chat_id) {
        this.chat_id = chat_id;
    }
}
