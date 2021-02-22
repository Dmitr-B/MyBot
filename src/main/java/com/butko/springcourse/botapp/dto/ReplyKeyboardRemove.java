package com.butko.springcourse.botapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.type.TrueFalseType;

@Data
public class ReplyKeyboardRemove {

    @JsonProperty("remove_keyboard")
    private boolean removeKeyboard = true;

}
