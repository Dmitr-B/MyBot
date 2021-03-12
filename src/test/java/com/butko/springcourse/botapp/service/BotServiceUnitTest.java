package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.telegram.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BotServiceUnitTest {

    private static final int TEST_CHAT_ID = 1;
    private static final String TEST_TEXT = "testText";
    private static final Keyboard TEST_KEYBOARD = new ForceReply();

    @InjectMocks
    private BotService botService;
    @Mock
    private MessageService messageService;
    @Mock
    private GameService gameService;

    @Test
    void handleUpdate_whenHasNotMessage_thenDoNotSendMessage() {
        Update update = new Update();

        botService.handleUpdate(update);

        verify(messageService, never()).sendMessage(anyInt(), anyString(), any());
    }

   /* @Test
    void handleUpdate_whenHasMessageAndHasText_thenSendMessage() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        SendMessage message1 = new SendMessage(TEST_CHAT_ID, TEST_TEXT, TEST_KEYBOARD);
        chat.setId(TEST_CHAT_ID);
        message.setMessageId(1);
        message.setText(TEST_TEXT);
        message.setChat(chat);
        update.setMessage(message);

        when(messageService.sendMessage(TEST_CHAT_ID, TEST_TEXT, TEST_KEYBOARD)).thenReturn(message1);

        botService.handleUpdate(update);

        verify(messageService).sendMessage(TEST_CHAT_ID, TEST_TEXT, TEST_KEYBOARD);
    }*/

    @Test
    void updateCallbackQuery_whenHasNotCallbackQuery_thenDoNotExist() {
        Update update = new Update();

        botService.updateCallbackQuery(update);

        verify(gameService, never()).updateStatInDB(any(), anyInt());
        verify(messageService, never()).sendMessage(anyInt(), anyString(), any());
        verify(messageService, never()).answerCallbackQuery(anyString(), anyString());
    }

    @Test
    void getOption_whenDataContain_thenOk() {
        List<String> testOption = List.of("Stone", "Scissors", "Paper");
        String expected = testOption.get(1);

        String actual = botService.getOption("Scissors");
        assertEquals(expected, actual);
    }

    //todo доробити декілька тестів
}
