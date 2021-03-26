package com.butko.springcourse.botapp.dto.factory;

import com.butko.springcourse.botapp.dto.telegram.Keyboard;
import com.butko.springcourse.botapp.dto.telegram.SendMessage;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SendMessageFactory extends MessageFactory{

    @Override
    public SendMessage createMessage(Integer chatId, String text, Keyboard keyboard) {
        return new SendMessage(chatId, text, keyboard);
    }
}
