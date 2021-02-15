package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.Update;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChatService {

    private final ChatRepository chatRepository;

    public void sendToDB(Update update) {
        Chat chat = new Chat();
        chat.setChatId(update.getMessage().getChat().getId());
        chat.setFirstName(update.getMessage().getChat().getFirstName());
        chat.setLastName(update.getMessage().getChat().getLastName());
        chat.setText(update.getMessage().getText());
        log.info("Save data to DB: " + chat);
        chatRepository.save(chat);
    }
}
