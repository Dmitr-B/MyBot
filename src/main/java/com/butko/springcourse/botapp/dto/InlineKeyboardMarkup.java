package com.butko.springcourse.botapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InlineKeyboardMarkup {

    @JsonProperty("inline_keyboard")
    private InlineKeyboardButton[][] inlineKeyboard;
}
