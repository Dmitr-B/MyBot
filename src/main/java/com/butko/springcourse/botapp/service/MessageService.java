package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.telegram.AnswerCallbackQuery;
import com.butko.springcourse.botapp.dto.telegram.Keyboard;
import com.butko.springcourse.botapp.dto.telegram.SendMessage;
import com.butko.springcourse.botapp.dto.telegram.Update;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.MessageRepository;
import com.butko.springcourse.botapp.repository.domain.Chat;
import com.butko.springcourse.botapp.repository.domain.Message;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Log4j2
public class MessageService {


    private final BotConfig botConfig;
    private final RestTemplate restTemplate;
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public SendMessage sendMessage(Integer id, String text, Keyboard keyboard){
        SendMessage message = new SendMessage(id, text, keyboard);
        restTemplate.postForObject(botConfig.getDomain() + botConfig.getToken() + "/sendMessage",
                message, SendMessage.class);
        return message;
    }

    public AnswerCallbackQuery answerCallbackQuery(String id, String text) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery(id, text);
        restTemplate.postForObject(botConfig.getDomain() + botConfig.getToken() + "/answerCallbackQuery",
                answerCallbackQuery, SendMessage.class);
        return answerCallbackQuery;
    }

    public void messageToDB(Update update) {
        if (update.hasMessage()) {
            Message message = new Message();
            Chat chat = new Chat();
            chat.setId(chatRepository.findByChatId(update.getMessage().getChat().getId()).get().getId());
            //chat.setChatId(update.getMessage().getChat().getId());
            //chat.setFirstName(update.getMessage().getChat().getFirstName());
            //chat.setLastName(update.getMessage().getChat().getLastName());
            //chat.setText(update.getMessage().getText());
            log.info("chuit" + chat);
            message.setMessageId(update.getMessage().getMessageId());
            message.setText(update.getMessage().getText());
            message.setChat(chat);
            messageRepository.save(message);
        }
    }
}
