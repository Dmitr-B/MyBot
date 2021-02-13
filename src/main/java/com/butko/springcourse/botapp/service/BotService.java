package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.config.BotConfig;
import com.butko.springcourse.botapp.dto.SendMessage;
import com.butko.springcourse.botapp.dto.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BotService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BotConfig botConfig;

    public void handleUpdate(Update update) {
        SendMessage message = new SendMessage(update.getMessage().getChat().getChat_id(), update.getMessage().getText());
        System.out.println(message);
        System.out.println(update.getUpdate_id());
        System.out.println(update.getMessage().getMessage_id());
        System.out.println(update.getMessage().getChat().getChat_id());
        restTemplate.postForObject("https://api.telegram.org/bot" + botConfig.getToken() + "/sendMessage", message, SendMessage.class);

    }

    }
