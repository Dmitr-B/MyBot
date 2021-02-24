package com.butko.springcourse.botapp.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KeyboardButton {

    @JsonProperty("text")
    private String text;

}
