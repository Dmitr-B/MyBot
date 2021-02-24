package com.butko.springcourse.botapp.dto.telegram;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("is_bot")
    private boolean isBot;

    @JsonProperty("first_name")
    private String firstName;

    @JsonCreator
    public User(Integer id, boolean isBot, String firstName) {
        this.id = id;
        this.isBot = isBot;
        this.firstName = firstName;
    }
}
