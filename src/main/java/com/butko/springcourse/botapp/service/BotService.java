package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.KeyboardButton;
import com.butko.springcourse.botapp.dto.ReplyKeyboardMarkup;
import com.butko.springcourse.botapp.dto.SendMessage;
import com.butko.springcourse.botapp.dto.Update;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.domain.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BotService {

    private final RestTemplate restTemplate;
    private final BotConfig botConfig;
    //private final ChatRepository chatRepository;
    //private final Chat chat;

    public void handleUpdate(Update update) {
            SendMessage message = new SendMessage(update.getMessage().getChat().getId(), update.getMessage().getText());
            restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage",
                    message, SendMessage.class);
    }

}
