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
    public int resultGame;
    //private final ChatRepository chatRepository;
    //private final Chat chat;

    public void handleUpdate(Update update) {
        if (update.getMessage().getText().equals("/start") || update.getMessage().getText().equals("Сыграть еще раз")) {
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
            restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage",
                    gameMessage,SendMessage.class);
        }
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
                    ReplyKeyboardMarkup answerMarkup = new ReplyKeyboardMarkup();
                    answerMarkup.setOneTimeKeyboard(true);
                    answerMarkup.setResizeKeyboard(true);
                    List<KeyboardButton> answerButtons = new ArrayList<>();
                    answerButtons.add(createKeyboardButton("Сыграть еще раз"));
                    List<List<KeyboardButton>> answerButtonList = new ArrayList<>();
                    answerButtonList.add(answerButtons);
                    answerMarkup.setKeyboard(answerButtonList);
                    SendMessage resultMessage = new SendMessage(update.getCallbackQuery().getMessage().getChat().getId(),
                            playGame(update.getCallbackQuery().getData()), answerMarkup);
                    restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage",
                            resultMessage,SendMessage.class);
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

    private KeyboardButton createKeyboardButton(String text) {
        KeyboardButton button = new KeyboardButton();
        button.setText(text);
        return button;
    }

    public String playGame(String data){
        String result = null;
        String[] optionBot = {"Stone","Scissors", "Paper"};
        String[] optionUser = {"Stone","Scissors", "Paper"};
        int indexBot = 0;
        int indexUser;
        Random randBot = new Random();
        Random randUser = new Random();
        if (data.equals("Stone") || data.equals("Scissors") || data.equals("Paper")) {
            indexBot = randBot.nextInt(3);
            result = getResult(data, optionBot[indexBot]);
        } else {
            indexUser = randUser.nextInt(3);
            result = getResult(optionUser[indexUser], optionBot[indexBot]);
        }
        return result;
    }

    private String getResult(String data, String random) {
        String result = null;
//        GameResult gameResult;
        if (data.equals("Stone") && random.equals("Stone")){
            result = String.format("\u270a" + " vs " + "\u270a" + "%nDraw");
            //gameResult = GameResult.DRAW;
            resultGame = 2;
        }
        if (data.equals("Stone") && random.equals("Scissors")){
            result = String.format("\u270a" + " vs " + "\u270c\ufe0f" + "%nWin user");
            //gameResult = GameResult.WON;
            resultGame = 1;
        }
        if (data.equals("Stone") && random.equals("Paper")){
            result = String.format("\u270a" + " vs " + "\ud83e\udd1a" + "%nWin bot");
            //gameResult = GameResult.LOSE;
            resultGame = 3;
        }
        if (data.equals("Scissors") && random.equals("Stone")){
            result = String.format("\u270c\ufe0f" + " vs " + "\u270a" + "%nWin bot");
            //gameResult = GameResult.LOSE;
            resultGame = 3;
        }
        if (data.equals("Scissors") && random.equals("Scissors")){
            result = String.format("\u270c\ufe0f" + " vs " + "\u270c\ufe0f" + "%nDraw");
            //gameResult = GameResult.DRAW;
            resultGame = 2;
        }
        if (data.equals("Scissors") && random.equals("Paper")){
            result = String.format("\u270c\ufe0f" + " vs " + "\ud83e\udd1a" + "%nWin user");
            //gameResult = GameResult.WON;
            resultGame = 1;
        }
        if (data.equals("Paper") && random.equals("Stone")){
            result = String.format("\ud83e\udd1a" + " vs " + "\u270a" + "%nWin user");
            //gameResult = GameResult.WON;
            resultGame = 1;
        }
        if (data.equals("Paper") && random.equals("Scissors")){
            result = String.format("\ud83e\udd1a" + " vs " + "\u270c\ufe0f" + "%nWin bot");
            //gameResult = GameResult.LOSE;
            resultGame = 3;
        }
        if (data.equals("Paper") && random.equals("Paper")){
            result = String.format("\ud83e\udd1a" + " vs " + "\ud83e\udd1a" + "%nDraw");
            //gameResult = GameResult.DRAW;
            resultGame = 2;
        }
        return result;
    }

}
