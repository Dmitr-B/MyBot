package com.butko.springcourse.botapp.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReplyKeyboardRemove extends Keyboard{

    @JsonProperty("remove_keyboard")
    private boolean removeKeyboard = true;

}
