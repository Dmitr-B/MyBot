package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.GameResult;
import com.butko.springcourse.botapp.dto.telegram.*;
import com.butko.springcourse.botapp.repository.GameRepository;
import com.butko.springcourse.botapp.repository.domain.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BotServiceUnitTest {

    private static final int TEST_CHAT_ID = 1;
    private static final String START_TEXT = "/start";
    private static final String STAT_TEXT = "Статистика";
    private static final String TEST_FIRST_NAME = "testFirstName";
    private static final Keyboard TEST_KEYBOARD = new ForceReply();

    @InjectMocks
    private BotService botService;
    @Mock
    private MessageService messageService;
    @Mock
    private GameService gameService;

    /*@Spy
    final BotService spyBotService = new BotService(gameService, messageService);*//* = spy(BotService.class);*/

    @Test
    void handleUpdate_whenHasNotMessage_thenDoNotSendMessage() {
        Update update = new Update();

        botService.handleUpdate(update);

        verify(messageService, never()).sendMessage(anyInt(), anyString(), any());
    }

    @Test
    void handleUpdate_whenHasMessageAndTextStart_thenSendMessage() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();

        InlineKeyboardMarkup gameMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> gameButtons = new ArrayList<>();
        List<InlineKeyboardButton> randomButton = new ArrayList<>();

        InlineKeyboardButton stone = new InlineKeyboardButton();
        stone.setText("\u270a");
        stone.setCallbackData("Stone");

        InlineKeyboardButton scissors = new InlineKeyboardButton();
        scissors.setText("\u270c\ufe0f");
        scissors.setCallbackData("Scissors");

        InlineKeyboardButton paper = new InlineKeyboardButton();
        paper.setText("\ud83e\udd1a");
        paper.setCallbackData("Paper");

        InlineKeyboardButton random = new InlineKeyboardButton();
        random.setText("Выбрать случайное значение");
        random.setCallbackData("Random");

        gameButtons.add(stone);
        gameButtons.add(scissors);
        gameButtons.add(paper);
        randomButton.add(random);

        List<List<InlineKeyboardButton>> gameButtonList = new ArrayList<>();
        gameButtonList.add(gameButtons);
        gameButtonList.add(randomButton);
        gameMarkup.setInlineKeyboard(gameButtonList);

        chat.setId(TEST_CHAT_ID);
        message.setMessageId(1);
        message.setText(START_TEXT);
        message.setChat(chat);
        update.setMessage(message);

        botService.handleUpdate(update);

        verify(messageService).sendMessage(TEST_CHAT_ID, "Выбирай свой вариант", gameMarkup);
    }

    @Test
    void handleUpdate_whenHasMessageAndTextStat_thenSendMessage() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();

        ReplyKeyboardMarkup answerMarkup = new ReplyKeyboardMarkup();
        answerMarkup.setOneTimeKeyboard(true);
        answerMarkup.setResizeKeyboard(true);

        KeyboardButton stat = new KeyboardButton();
        stat.setText("Статистика");

        KeyboardButton tryAgain = new KeyboardButton();
        tryAgain.setText("Сыграть еще раз");

        List<KeyboardButton> answerButtons = new ArrayList<>();
        answerButtons.add(tryAgain);
        answerButtons.add(stat);

        List<List<KeyboardButton>> answerButtonList = new ArrayList<>();
        answerButtonList.add(answerButtons);
        answerMarkup.setKeyboard(answerButtonList);

        SendMessage message1 = new SendMessage(TEST_CHAT_ID, gameService.showStat(TEST_CHAT_ID), answerMarkup);
        chat.setId(TEST_CHAT_ID);
        message.setMessageId(1);
        message.setText(STAT_TEXT);
        message.setChat(chat);
        update.setMessage(message);

        when(messageService.sendMessage(TEST_CHAT_ID, gameService.showStat(TEST_CHAT_ID), answerMarkup)).thenReturn(message1);

        botService.handleUpdate(update);

        verify(messageService).sendMessage(TEST_CHAT_ID, gameService.showStat(TEST_CHAT_ID), answerMarkup);
    }

    @Test
    void updateCallbackQuery_whenHasNotCallbackQuery_thenDoNotExist() {
        Update update = new Update();

        botService.updateCallbackQuery(update);

        verify(gameService, never()).sendStatToDB(any(), anyInt(), anyString());
        verify(messageService, never()).sendMessage(anyInt(), anyString(), any());
        verify(messageService, never()).answerCallbackQuery(anyString(), anyString());
    }

    /*@Test
    void updateCallbackQuery_whenCallbackQueryHasStone_thenExist() {
        Update update = new Update();
        CallbackQuery callbackQuery = new CallbackQuery();
        Message message = new Message();
        Chat chat = new Chat();
        callbackQuery.setData("Stone");
        update.setCallbackQuery(callbackQuery);
        update.setUpdateId(TEST_CHAT_ID);
        update.setMessage(message);
        update.setEditedMessage(message);
        chat.setId(TEST_CHAT_ID);
        chat.setFirstName(TEST_FIRST_NAME);
        message.setChat(chat);
        callbackQuery.setMessage(message);
        callbackQuery.setId("1");

        ReplyKeyboardMarkup answerMarkup = new ReplyKeyboardMarkup();
        answerMarkup.setOneTimeKeyboard(true);
        answerMarkup.setResizeKeyboard(true);

        KeyboardButton stat = new KeyboardButton();
        stat.setText("Статистика");

        KeyboardButton tryAgain = new KeyboardButton();
        tryAgain.setText("Сыграть еще раз");

        List<KeyboardButton> answerButtons = new ArrayList<>();
        answerButtons.add(tryAgain);
        answerButtons.add(stat);

        List<List<KeyboardButton>> answerButtonList = new ArrayList<>();
        answerButtonList.add(answerButtons);
        answerMarkup.setKeyboard(answerButtonList);

        spyBotService.updateCallbackQuery(update);

        verify(gameService).sendStatToDB(GameResult.WON, TEST_CHAT_ID, TEST_FIRST_NAME);
        //verify(messageService).sendMessage(TEST_CHAT_ID, "testText", answerMarkup);
        //verify(messageService, never()).answerCallbackQuery(anyString(), anyString());
    }*/

    @Test
    void getOption_whenDataContain_thenOk() {
        List<String> testOption = List.of("Stone", "Scissors", "Paper");
        String expected = testOption.get(1);

        String actual = botService.getOption("Scissors");
        assertEquals(expected, actual);
    }

    //todo доробити декілька тестів
}
