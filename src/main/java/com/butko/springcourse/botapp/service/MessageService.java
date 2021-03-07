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
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MessageService {


    private final BotConfig botConfig;
    private final RestTemplate restTemplate;
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;

    public SendMessage sendMessage(Integer chatId, String text, Keyboard keyboard){
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

    public void updateMessageDB(Update update) {
        if (update.hasEditedMessage()){
            Message replaceText = messageRepository.findByMessageId(update.getEditedMessage().getMessageId());
            replaceText.setText(update.getEditedMessage().getText());
            log.info("Updated data to DB: " + replaceText);
            messageRepository.save(replaceText);
        }
    }

    public Message getById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    /*public List<Message> test1() {
        List<Message> m = messageRepository.findAll();
    }

    public List<Chat> test() {
        return messageRepository.findAll().stream()
                .skip(15)
                .filter(message -> message.getChat().getId().equals(1L))
                .limit(10)
                .map(Message::getChat)
                .collect(Collectors.toList());
    }*/

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
