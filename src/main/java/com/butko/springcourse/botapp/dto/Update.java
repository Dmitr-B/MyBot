package com.butko.springcourse.botapp.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Update {
    @JsonProperty("update_id")
    private Integer update_id;

    @JsonProperty("message")
    private Message message;

    @JsonCreator
    public Update(Integer update_id, Message message) {
        this.update_id = update_id;
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Integer getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Integer update_id) {
        this.update_id = update_id;
    }

    @Override
    public String toString() {
        return "Update{" +
                "message=" + message +
                '}';
    }
}
