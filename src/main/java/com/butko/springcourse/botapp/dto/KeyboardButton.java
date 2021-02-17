package com.butko.springcourse.botapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KeyboardButton {

    @JsonProperty("text")
    private String text;

    public KeyboardButton(String text) {
        this.text = text;
    }
}
