package com.butko.springcourse.botapp.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
public class InlineKeyboardButton {

    @JsonProperty("text")
    private String text;

    @JsonProperty("switch_inline_query")
    private String switchInlineQuery;

    @JsonProperty("callback_data")
    private String callbackData;
}
