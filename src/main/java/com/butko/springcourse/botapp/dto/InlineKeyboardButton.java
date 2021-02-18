package com.butko.springcourse.botapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InlineKeyboardButton {

    @JsonProperty("text")
    private String text;

    @JsonProperty("switch_inline_query")
    private String switchInlineQuery;
}
