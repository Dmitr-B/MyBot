package com.butko.springcourse.botapp.dto.factory;

import com.butko.springcourse.botapp.dto.telegram.Keyboard;
import com.butko.springcourse.botapp.dto.telegram.SendMessage;

public abstract class MessageFactory {

    public abstract SendMessage createMessage(Integer chatId, String text, Keyboard keyboard);

}
