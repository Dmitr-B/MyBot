package com.butko.springcourse.botapp.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ForceReply extends Keyboard{

    @JsonProperty("force_reply")
    private boolean forceReply = true;
}