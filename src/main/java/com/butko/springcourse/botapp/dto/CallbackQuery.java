package com.butko.springcourse.botapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CallbackQuery {

    @JsonProperty("id")
    private String id;

    @JsonProperty("from")
    private User from;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("data")
    private String data;
}
