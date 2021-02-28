package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.telegram.Update;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatService {

    private final ChatRepository chatRepository;

    public void sendToDB(Update update) {
        if (update.hasMessage()){
            if (chatRepository.findByChatId(update.getMessage().getChat().getId()).isEmpty()) {
                Chat chat = new Chat();
                chat.setChatId(update.getMessage().getChat().getId());
                chat.setFirstName(update.getMessage().getChat().getFirstName());
                chat.setLastName(update.getMessage().getChat().getLastName());
                chat.setText(update.getMessage().getText());
                log.info("Save data to DB: " + chat);
                chatRepository.save(chat);
            } else log.info("ChatId is already defined");
        }
    }

    public void updateDB(Update update) {
        if (update.hasEditedMessage()){
            Chat replaceText = chatRepository.findByChatId(update.getEditedMessage().getChat().getId()).get();
            replaceText.setText(update.getEditedMessage().getText());
            log.info("Updated data to DB: " + replaceText);
            chatRepository.save(replaceText);
        }
       }

    public Chat getById(Long id) {
        return chatRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    public void saveChat(Chat chat) {
        chatRepository.save(chat);
    }

    public void update(Long id, Chat updateChat) {
        Chat chat = getById(id);
        chat.setChatId(updateChat.getChatId());
        chat.setFirstName(updateChat.getFirstName());
        chat.setLastName(updateChat.getLastName());
        chat.setText(updateChat.getText());
        chatRepository.save(chat);
    }

    public void delete(Long id) {
        chatRepository.deleteById(id);
    }
}
