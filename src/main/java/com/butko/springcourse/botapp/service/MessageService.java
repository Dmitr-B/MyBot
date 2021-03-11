package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.telegram.AnswerCallbackQuery;
import com.butko.springcourse.botapp.dto.telegram.Keyboard;
import com.butko.springcourse.botapp.dto.telegram.SendMessage;
import com.butko.springcourse.botapp.dto.telegram.Update;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.MessageRepository;
import com.butko.springcourse.botapp.repository.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MessageService {

    private final BotConfig botConfig;
    private final RestTemplate restTemplate;
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public SendMessage sendMessage(Integer chatId, String text, Keyboard keyboard) {
        SendMessage message = new SendMessage(chatId, text, keyboard);

        restTemplate.postForObject(botConfig.getDomain() + botConfig.getToken() + "/sendMessage",
                message, SendMessage.class);

        return message;
    }

    public void answerCallbackQuery(String id, String text) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(id, text);

        restTemplate.postForObject(botConfig.getDomain() + botConfig.getToken() + "/answerCallbackQuery",
                answerCallbackQuery, SendMessage.class);
    }

    public void messageToDB(Update update) {
        if (update.hasMessage()) {
            chatRepository.findByChatId(update.getMessage().getChat().getId())
                    .ifPresent(chat -> {
                        Message message = new Message();
                        message.setMessageId(update.getMessage().getMessageId());
                        message.setText(update.getMessage().getText());
                        message.setChat(chat);
                        messageRepository.save(message);
                    });
        }
    }

    public void updateMessageDB(Update update) {
        if (update.hasEditedMessage()) {
            messageRepository.findByMessageId(update.getEditedMessage().getMessageId())
                    .ifPresent(messageToUpdate -> {
                        messageToUpdate.setText(update.getEditedMessage().getText());
                        log.info("Updated data to DB: " + messageToUpdate);
                        messageRepository.save(messageToUpdate);
                    });
        }
    }

    public Message getById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID: " + id));
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public void updateMessage(Long id, Message updateMessage) {
        Message message = getById(id);
        message.setMessageId(updateMessage.getMessageId());
        message.setText(updateMessage.getText());
        messageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
