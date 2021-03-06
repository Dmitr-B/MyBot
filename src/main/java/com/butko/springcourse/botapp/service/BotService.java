package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.GameResult;
import com.butko.springcourse.botapp.dto.game.GameContext;
import com.butko.springcourse.botapp.dto.game.Paper;
import com.butko.springcourse.botapp.dto.game.Scissors;
import com.butko.springcourse.botapp.dto.game.Stone;
import com.butko.springcourse.botapp.dto.telegram.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Log4j2
public class BotService {

    private final GameService gameService;
    private final MessageService messageService;

    private final List<String> options = List.of("Stone", "Scissors", "Paper");

    public void handleUpdate(Update update) {
        if (update.hasMessage()){
            switch (update.getMessage().getText()) {
                case "/start":
                case "Сыграть еще раз":
                    getChoiceMessage(update.getMessage().getChat().getId());
                    break;
                case "Статистика":
                    messageService.sendMessage(update.getMessage().getChat().getId(),
                            gameService.showStat(update.getMessage().getChat().getId()),createReplyMarkup());
                    break;
                default:
                    messageService.sendMessage(update.getMessage().getChat().getId(),
                            "Я пока что не могу отвечать на все сообщения. Давай играть", createReplyMarkup());
                    break;
            }
        }
    }

    private SendMessage getChoiceMessage(Integer chatId) {
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

        return messageService.sendMessage(chatId, "Выбирай свой вариант", gameMarkup);
    }

    public void updateCallbackQuery(Update update) {

        if (update.hasCallbackQuery()) {
            log.info("Callback post: " + update.getCallbackQuery());
            String userChoice = getOption(update.getCallbackQuery().getData());
            String botChoice = getOption("Random");
            GameResult gameResult = playGame(userChoice, botChoice);
            String message = userChoice + " vs " + botChoice + " " + gameResult.toString();

            gameService.sendStatToDB(gameResult, update.getCallbackQuery().getMessage().getChat().getId(),
                    update.getCallbackQuery().getMessage().getChat().getFirstName());

            messageService.sendMessage(update.getCallbackQuery().getMessage().getChat().getId(),
                    message, createReplyMarkup());

            messageService.answerCallbackQuery(update.getCallbackQuery().getId(),
                    update.getCallbackQuery().getData());
        }
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

    private ReplyKeyboardMarkup createReplyMarkup() {
        ReplyKeyboardMarkup answerMarkup = new ReplyKeyboardMarkup();
        answerMarkup.setOneTimeKeyboard(true);
        answerMarkup.setResizeKeyboard(true);

        List<KeyboardButton> answerButtons = new ArrayList<>();
        answerButtons.add(createKeyboardButton("Сыграть еще раз"));
        answerButtons.add(createKeyboardButton("Статистика"));

        List<List<KeyboardButton>> answerButtonList = new ArrayList<>();
        answerButtonList.add(answerButtons);
        answerMarkup.setKeyboard(answerButtonList);
        return answerMarkup;
    }

    public String getOption(String data) {
        if (options.contains(data)) {
            return data;
        }
        Random random = new Random();
        return options.get(random.nextInt(3));
    }

    private GameResult playGame(String choice1, String choice2) {
        GameContext gameContext = new GameContext();

        switch (choice1){
            case "Stone":
                gameContext.setGameStrategy(new Stone());
            break;
            case "Scissors":
                gameContext.setGameStrategy(new Scissors());
                break;
            case "Paper":
                gameContext.setGameStrategy(new Paper());
                break;
        }

        return gameContext.playGame(choice2);
    }
}