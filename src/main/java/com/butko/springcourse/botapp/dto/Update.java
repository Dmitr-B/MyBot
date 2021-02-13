package com.butko.springcourse.botapp.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
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

}
