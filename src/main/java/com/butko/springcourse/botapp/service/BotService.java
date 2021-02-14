package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.SendMessage;
import com.butko.springcourse.botapp.dto.Update;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.domain.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BotService {

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;
    private final ChatRepository chatRepository;
    //private final Chat chat;

    public void handleUpdate(Update update) {
        SendMessage message = new SendMessage(update.getMessage().getChat().getId(), update.getMessage().getText());
        System.out.println(message);
        Chat chat = new Chat();
        System.out.println(chat);
        chatRepository.save(chat);
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", message, SendMessage.class);
    }

}
