package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.telegram.Update;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatService {

    private final ChatRepository chatRepository;

    public void sendToDB(Update update) {
        if (update.hasMessage()) {
            Optional<Chat> chat = chatRepository.findByChatId(update.getMessage().getChat().getId());
            if (chat.isEmpty()) {
                Chat newChat = new Chat();
                newChat.setChatId(update.getMessage().getChat().getId());
                newChat.setFirstName(update.getMessage().getChat().getFirstName());
                newChat.setLastName(update.getMessage().getChat().getLastName());
                log.info("Save data to DB: " + chat);
                chatRepository.save(newChat);
            } else {
                chat.ifPresent(chat1 -> log.info("messages: {}", chat1.getMessages()));
                log.info("ChatId is already defined");
            }
        }
    }

    public Chat getById(Long id) {
        return chatRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID: " + id));
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
        chatRepository.save(chat);
    }

    public void delete(Long id) {
        chatRepository.deleteById(id);
    }
}
