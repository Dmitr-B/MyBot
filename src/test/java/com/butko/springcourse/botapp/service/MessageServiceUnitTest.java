package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.telegram.*;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.MessageRepository;
import com.butko.springcourse.botapp.repository.domain.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class MessageServiceUnitTest {

    private static final String TEST_DOMAIN = "https://api.telegram.org/bot";
    private static final String TEST_TOKEN = "1661234049:asdfghjkl";
    private static final Integer TEST_CHAT_ID = 123;
    private static final String TEST_TEXT = "Hello";
    private static final String TEST_QUERY_ID = "Test_id";
    private static final Long TEST_ID = 1L;
    private static final Keyboard TEST_KEYBOARD = new ForceReply();

    @InjectMocks
    private MessageService messageService;
    @Mock
    private BotConfig botConfig;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private ChatRepository chatRepository;

    //1. дописуєш тести (і на контроллери)
    //2. пробуєш інтеграційні тести

    @Test
    @DisplayName("Single test successful")
    void sendMessage_whenValidData_thenOk() {
        SendMessage expected = new SendMessage(TEST_CHAT_ID, TEST_TEXT, TEST_KEYBOARD);

        when(botConfig.getDomain()).thenReturn(TEST_DOMAIN);
        when(botConfig.getToken()).thenReturn(TEST_TOKEN);

        SendMessage actual = messageService.sendMessage(TEST_CHAT_ID, TEST_TEXT, TEST_KEYBOARD);
        assertEquals(expected, actual);

        verify(restTemplate).postForObject("https://api.telegram.org/bot1661234049:asdfghjkl/sendMessage", expected, SendMessage.class);
    }

    @Test
    void sendMessage_whenRestClientException_thenThrow() {
        String expectedMessage = "test";
        when(restTemplate.postForObject(anyString(), any(), any())).thenThrow(new RestClientException(expectedMessage));

        RestClientException actual = assertThrows(RestClientException.class,
                () -> messageService.sendMessage(TEST_CHAT_ID, TEST_TEXT, TEST_KEYBOARD));
        assertEquals(expectedMessage, actual.getMessage());

        verify(restTemplate).postForObject(anyString(), any(), any());
    }

    @Test
    @DisplayName("CallbackQuery test successful")
    void answerCallbackQuery_whenValidData_thenOk() {
        when(botConfig.getDomain()).thenReturn(TEST_DOMAIN);
        when(botConfig.getToken()).thenReturn(TEST_TOKEN);

        messageService.answerCallbackQuery(TEST_QUERY_ID, TEST_TEXT);

        verify(restTemplate).postForObject("https://api.telegram.org/bot1661234049:asdfghjkl/answerCallbackQuery",
                new AnswerCallbackQuery(TEST_QUERY_ID, TEST_TEXT), SendMessage.class);
    }

    @Test
    void answerCallbackQuery_whenRestClientException_thenThrow() {
        String expectedMessage = "test";
        when(restTemplate.postForObject(anyString(), any(), any())).thenThrow(new RestClientException(expectedMessage));

        RestClientException actual = assertThrows(RestClientException.class,
                () -> messageService.answerCallbackQuery(TEST_QUERY_ID, TEST_TEXT));
        assertEquals(expectedMessage, actual.getMessage());

        verify(restTemplate).postForObject(anyString(), any(), any());
    }

    @Test
    void sendMessageToDB_whenHasMessageAndChatDoesNotExist_thenDoNotSave() {
        com.butko.springcourse.botapp.dto.telegram.Chat chat = new com.butko.springcourse.botapp.dto.telegram.Chat();
        chat.setId(1234);

        com.butko.springcourse.botapp.dto.telegram.Message message = new com.butko.springcourse.botapp.dto.telegram.Message();
        message.setMessageId(4565);
        message.setText(TEST_TEXT);
        message.setChat(chat);

        Update update = new Update();
        update.setMessage(message);

        when(chatRepository.findByChatId(1234)).thenReturn(Optional.empty());

        messageService.messageToDB(update);

        verify(messageRepository, never()).save(any());
    }

    //todo додати 2 тести

    @Test
    void saveMessage_whenSave_thenOk() {
        Message message = new Message();
        message.setId(TEST_ID);
        message.setMessageId(123);
        message.setText(TEST_TEXT);

        messageService.saveMessage(message);

        verify(messageRepository).save(message);
    }

    @Test
    void getAllMessage_whenGet_thenOk() {
        List<Message> expected = new ArrayList<>();
        Message message = new Message();
        message.setId(TEST_ID);
        message.setMessageId(123);
        message.setText(TEST_TEXT);
        expected.add(message);

        when(messageRepository.findAll()).thenReturn(expected);

        List<Message> actual = messageService.getAll();
        assertEquals(expected, actual);

        verify(messageRepository).findAll();
    }

    @Test
    void getMessageById_whenExists_thenOk() {
        Message expected = new Message();
        expected.setId(1L);
        expected.setMessageId(123);
        expected.setText(TEST_TEXT);
        when(messageRepository.findById(TEST_ID)).thenReturn(Optional.of(expected));

        Message actual = messageService.getById(TEST_ID);
        assertEquals(expected, actual);

        verify(messageRepository).findById(TEST_ID);
    }

    @Test
    void getMessageById_whenDoesNotExist_thenThrow() {
        when(messageRepository.findById(TEST_ID)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> messageService.getById(TEST_ID));

        verify(messageRepository).findById(TEST_ID);
    }

    @Test
    void updateMessage_whenUpdate_thenOk() {
        Message message = new Message();
        message.setText("text1");
        Message messageUpdate = new Message();
        messageUpdate.setText("text2");

        when(messageRepository.findById(TEST_ID)).thenReturn(Optional.of(message));

        messageService.updateMessage(TEST_ID, messageUpdate);

        verify(messageRepository).save(messageUpdate);
    }

    @Test
    void deleteMessage_whenDelete_thenOk() {
        Message message = new Message();
        message.setId(TEST_ID);

        messageService.deleteMessage(TEST_ID);

        verify(messageRepository).deleteById(TEST_ID);
    }
}
