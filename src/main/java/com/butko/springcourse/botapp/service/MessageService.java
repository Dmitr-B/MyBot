package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.telegram.AnswerCallbackQuery;
import com.butko.springcourse.botapp.dto.telegram.Keyboard;
import com.butko.springcourse.botapp.dto.telegram.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MessageService {

    public SendMessage sendMessage(Integer id, String text, Keyboard keyboard){
        return new SendMessage(id, text, keyboard);
    }

    public AnswerCallbackQuery answerCallbackQuery(String id, String text) {
        return new AnswerCallbackQuery(id, text);
    }
}
