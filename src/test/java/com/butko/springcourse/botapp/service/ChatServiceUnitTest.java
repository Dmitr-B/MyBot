package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.telegram.Message;
import com.butko.springcourse.botapp.dto.telegram.Update;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.domain.Chat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatServiceUnitTest {

    private static final long TEST_ID = 1;
    private static final long TEST_CHAT_ID = 123;
    private static final String TEST_FIRST_NAME = "testFirstName";
    private static final String TEST_LAST_NAME = "testLastName";

    @InjectMocks
    private ChatService chatService;
    @Mock
    private ChatRepository chatRepository;

    @Test
    void sendToDB_whenHasNotMessage_thenDoNotSave() {
        Update update = new Update();

        chatService.sendToDB(update);

        verify(chatRepository, never()).save(any());
    }

    @Test
    void sendToDB_whenHasMessageAndHasChat_thenDoNotSave() {
        Message message = new Message();
        com.butko.springcourse.botapp.dto.telegram.Chat messageChat = new com.butko.springcourse.botapp.dto.telegram.Chat();
        messageChat.setId(1);
        message.setChat(messageChat);

        Update update = new Update();
        update.setMessage(message);

        Chat chat = new Chat();
        chat.setId(TEST_ID);
        chat.setChatId(TEST_ID);

        when(chatRepository.findByChatId(TEST_ID)).thenReturn(Optional.of(chat));
        chatService.sendToDB(update);

        verify(chatRepository, never()).save(any());
    }

    @Test
    void sendToDB_whenHasMessageAndHasNotChat_thenSave() {
        Message message = new Message();
        com.butko.springcourse.botapp.dto.telegram.Chat messageChat = new com.butko.springcourse.botapp.dto.telegram.Chat();
        messageChat.setId(1);
        message.setChat(messageChat);

        Update update = new Update();
        update.setMessage(message);

        Chat chat = new Chat();
        chat.setChatId(TEST_ID);

        when(chatRepository.findByChatId(TEST_ID)).thenReturn(Optional.empty());
        chatService.sendToDB(update);

        verify(chatRepository).save(chat);
    }

    @Test
    void getAllChat_whenExists_theOk() {
        List<Chat> expected = new ArrayList<>();
        Chat chat = new Chat();
        chat.setId(TEST_ID);
        chat.setChatId(TEST_CHAT_ID);
        chat.setFirstName(TEST_FIRST_NAME);
        chat.setLastName(TEST_LAST_NAME);
        expected.add(chat);

        when(chatRepository.findAll()).thenReturn(expected);

        List<Chat> actual = chatService.getAll();
        assertEquals(expected, actual);

        verify(chatRepository).findAll();
    }

    @Test
    void getChatById_whenExists_theOk() {
        Chat expected = new Chat();
        expected.setId(TEST_ID);
        expected.setChatId(TEST_CHAT_ID);
        expected.setFirstName(TEST_FIRST_NAME);
        expected.setLastName(TEST_LAST_NAME);

        when(chatRepository.findById(TEST_ID)).thenReturn(Optional.of(expected));

        Chat actual = chatService.getById(TEST_ID);
        assertEquals(expected, actual);

        verify(chatRepository).findById(TEST_ID);
    }

    @Test
    void getChatById_whenDoesNotExists_thenThrow() {
        String expectedMessage = "not found";
        when(chatRepository.findById(TEST_ID)).thenThrow(new EntityNotFoundException(expectedMessage));

        EntityNotFoundException actual = assertThrows(EntityNotFoundException.class,
                () -> chatService.getById(TEST_ID));
        assertEquals(expectedMessage, actual.getMessage());

        verify(chatRepository).findById(TEST_ID);
    }

    @Test
    void saveChat_whenExists_theOk() {
        Chat chat = new Chat();
        chat.setId(TEST_ID);
        chat.setChatId(TEST_CHAT_ID);
        chat.setFirstName(TEST_FIRST_NAME);
        chat.setLastName(TEST_LAST_NAME);

        chatService.saveChat(chat);

        verify(chatRepository).save(chat);
    }

    @Test
    void updateChat_whenExists_theOk() {
        Chat chat = new Chat();
        chat.setId(TEST_ID);
        chat.setChatId(TEST_CHAT_ID);
        chat.setFirstName(TEST_FIRST_NAME);
        chat.setLastName(TEST_LAST_NAME);

        Chat updatedChat = new Chat();
        updatedChat.setId(TEST_ID);
        updatedChat.setChatId(TEST_CHAT_ID);
        updatedChat.setFirstName(TEST_FIRST_NAME);
        updatedChat.setLastName("updatedLastName");

        when(chatRepository.findById(TEST_ID)).thenReturn(Optional.of(chat));

        chatService.update(TEST_ID, updatedChat);

        verify(chatRepository).save(updatedChat);
    }

    @Test
    void deleteChat_whenExists_theOk() {
        Chat chat = new Chat();
        chat.setId(TEST_ID);

        chatService.delete(TEST_ID);

        verify(chatRepository).deleteById(TEST_ID);
    }

}
