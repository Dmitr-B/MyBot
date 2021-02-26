package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.telegram.AnswerCallbackQuery;
import com.butko.springcourse.botapp.dto.telegram.Keyboard;
import com.butko.springcourse.botapp.dto.telegram.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Log4j2
public class MessageService {

    @Autowired
    private BotConfig botConfig;
    @Autowired
    private RestTemplate restTemplate;

    public SendMessage sendMessage(Integer id, String text, Keyboard keyboard){
        SendMessage message = new SendMessage(id, text, keyboard);
        restTemplate.postForObject(botConfig.getDomain() + botConfig.getToken() + "/sendMessage",
                message, SendMessage.class);
        return message;
    }

    public AnswerCallbackQuery answerCallbackQuery(String id, String text) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(id, text);
        restTemplate.postForObject(botConfig.getDomain() + botConfig.getToken() + "/answerCallbackQuery",
                answerCallbackQuery, SendMessage.class);
        return answerCallbackQuery;
    }
}
