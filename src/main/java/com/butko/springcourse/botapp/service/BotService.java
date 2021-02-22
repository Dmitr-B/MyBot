package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        if (update.getMessage().getText().equals("/start")) {
            InlineKeyboardMarkup gameMarkup = new InlineKeyboardMarkup();
            List<InlineKeyboardButton> gameButtons = new ArrayList<>();
            List<InlineKeyboardButton> randomButton = new ArrayList<>();
            gameButtons.add(createInlineButton("\u270a", "Stone"));
            gameButtons.add(createInlineButton("\u270c\ufe0f", "Scissors"));
            gameButtons.add(createInlineButton("\ud83e\udd1a", "Paper"));
            randomButton.add(createInlineButton("Выбрать случайное значение", "Random"));
            List<List<InlineKeyboardButton>> gameButtonList = new ArrayList<>();
            gameButtonList.add(gameButtons);
            gameButtonList.add(randomButton);
            gameMarkup.setInlineKeyboard(gameButtonList);
            SendMessage gameMessage = new SendMessage(update.getMessage().getChat().getId(),"Выбирай свой вариант",
                    gameMarkup);
            gameMessage.setReplyMarkup(gameMarkup);
            log.info("game " + gameMessage);
            restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage",
                    gameMessage,SendMessage.class);
            log.info("upd " + update);
        }
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<InlineKeyboardButton> buttons = new ArrayList<>();
//        buttons.add(createInlineButton("hi", "hi"));
//        buttons.add(createInlineButton("how are you?", "how are you?"));
//        List<List<InlineKeyboardButton>> buttonList = new ArrayList<>();
//        buttonList.add(buttons);
//        inlineKeyboardMarkup.setInlineKeyboard(buttonList);
//            SendMessage message = new SendMessage(update.getMessage().getChat().getId(), update.getMessage().getText()/*,inlineKeyboardMarkup*/);
//            message.setReplyMarkup(inlineKeyboardMarkup);
//            restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage",
//                    message, SendMessage.class);
    }

    public void updateCallbackQuery(Update update) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(update.getCallbackQuery().getId(),"I`m fine");
            log.info(answerCallbackQuery);
        if(update.getCallbackQuery()!=null) {
            log.info("Callback post: " + update.getCallbackQuery());
            switch (update.getCallbackQuery().getData()) {
                case "Stone":
                case "Scissors":
                case "Paper":
                case "Random":
                    SendMessage gameMessage = new SendMessage(update.getCallbackQuery().getMessage().getChat().getId(),
                            playGame(update.getCallbackQuery().getData()), new ReplyKeyboardRemove());
                    restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage",
                            gameMessage,SendMessage.class);
                break;
            }
        }
            restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/answerCallbackQuery",
                    answerCallbackQuery,AnswerCallbackQuery.class);
    }

    private InlineKeyboardButton createInlineButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }

    private String playGame(String data){
        String result = null;
        String[] optionBot = {"Stone","Scissors", "Paper"};
        String[] optionUser = {"Stone","Scissors", "Paper"};
        int indexBot = 0;
        int indexUser = 0;
        Random randBot = new Random();
        Random randUser = new Random();
        if (data.equals("Stone") || data.equals("Scissors") || data.equals("Paper")) {
            indexBot = randBot.nextInt(3);
            result = getResult(data, optionBot[indexBot]);
        }
        if (data.equals("Random")) {
            indexUser = randUser.nextInt(3);
            result = getResult(optionUser[indexUser], optionBot[indexBot]);
        }
        return result;
    }

    private String getResult(String data, String random) {
        String result = null;
        if (data.equals("Stone") && random.equals("Stone")){
            result = String.format("\u270a" + " vs " + "\u270a" + "%nDraw");
        }
        if (data.equals("Stone") && random.equals("Scissors")){
            result = String.format("\u270a" + " vs " + "\u270c\ufe0f" + "%nWin user");
        }
        if (data.equals("Stone") && random.equals("Paper")){
            result = String.format("\u270a" + " vs " + "\ud83e\udd1a" + "%nWin bot");
        }
        if (data.equals("Scissors") && random.equals("Stone")){
            result = String.format("\u270c\ufe0f" + " vs " + "\u270a" + "%nWin bot");
        }
        if (data.equals("Scissors") && random.equals("Scissors")){
            result = String.format("\u270c\ufe0f" + " vs " + "\u270c\ufe0f" + "%nDraw");
        }
        if (data.equals("Scissors") && random.equals("Paper")){
            result = String.format("\u270c\ufe0f" + " vs " + "\ud83e\udd1a" + "%nWin user");
        }
        if (data.equals("Paper") && random.equals("Stone")){
            result = String.format("\ud83e\udd1a" + " vs " + "\u270a" + "%nWin user");
        }
        if (data.equals("Paper") && random.equals("Scissors")){
            result = String.format("\ud83e\udd1a" + " vs " + "\u270c\ufe0f" + "%nWin bot");
        }
        if (data.equals("Paper") && random.equals("Paper")){
            result = String.format("\ud83e\udd1a" + " vs " + "\ud83e\udd1a" + "%nDraw");
        }
        return result;
    }

}
