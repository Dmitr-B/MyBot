package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.telegram.ForceReply;
import com.butko.springcourse.botapp.dto.telegram.Keyboard;
import com.butko.springcourse.botapp.dto.telegram.SendMessage;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.MessageRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class MessageServiceUnitTest {

    private static final String TEST_DOMAIN = "https://api.telegram.org/bot";
    private static final String TEST_TOKEN = "1661234049:asdfghjkl";
    private static final Integer TEST_CHAT_ID = 123;
    private static final String TEST_TEXT = "Hello";
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
}
