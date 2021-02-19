package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.*;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BotService {

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;
    //private final ChatRepository chatRepository;
    //private final Chat chat;

    public void handleUpdate(Update update) {
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        KeyboardButton button1 = new KeyboardButton();
//        KeyboardButton button2 = new KeyboardButton();
//        button1.setText("Hi");
//        button2.setText("How are you?");
//        KeyboardButton[][] buttons = {{button1,button2}};
//        replyKeyboardMarkup.setKeyboard(buttons);
        switch (update.getMessage().getText()) {
            case "Хочу сыграть в игру":
                InlineKeyboardMarkup gameMarkup = new InlineKeyboardMarkup();
                List<InlineKeyboardButton> gameButtons = new ArrayList<>();
                gameButtons.add(createInlineButton("Камень", "Камень"));
                gameButtons.add(createInlineButton("Ножницы", "Ножницы"));
                gameButtons.add(createInlineButton("Бумага", "Бумага"));
                List<List<InlineKeyboardButton>> gameButtonList = new ArrayList<>();
                gameButtonList.add(gameButtons);
                gameMarkup.setInlineKeyboard(gameButtonList);
                SendMessage gameMessage = new SendMessage(update.getMessage().getChat().getId(),"Выбирай свой вариант");
                gameMessage.setReplyMarkup(gameMarkup);
                restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage",
                        gameMessage,SendMessage.class);
                break;
        }
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        buttons.add(createInlineButton("hi", "hi"));
        buttons.add(createInlineButton("how are you?", "how are you?"));
        List<List<InlineKeyboardButton>> buttonList = new ArrayList<>();
        buttonList.add(buttons);
        inlineKeyboardMarkup.setInlineKeyboard(buttonList);
            SendMessage message = new SendMessage(update.getMessage().getChat().getId(), update.getMessage().getText()/*,inlineKeyboardMarkup*/);
            message.setReplyMarkup(inlineKeyboardMarkup);
            restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage",
                    message, SendMessage.class);
    }

    public void updateCallbackQuery(Update update) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(update.getCallbackQuery().getId(),"I`m fine");
            log.info(answerCallbackQuery);
            restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/answerCallbackQuery",
                    answerCallbackQuery,AnswerCallbackQuery.class);
    }

    private InlineKeyboardButton createInlineButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }

}
