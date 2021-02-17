package com.butko.springcourse.botapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ReplyKeyboardMarkup {

    @JsonProperty("keyboard")
    private KeyboardButton[][] keyboard;

    @JsonProperty("resize_keyboard")
    private boolean resizeKeyboard;

    @JsonProperty("one_time_keyboard")
    private boolean oneTimeKeyboard;
}
