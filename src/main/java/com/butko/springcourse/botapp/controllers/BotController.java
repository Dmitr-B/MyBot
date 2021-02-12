package com.butko.springcourse.botapp.controllers;

import com.butko.springcourse.botapp.Message;
import com.butko.springcourse.botapp.SendMessage;
import com.butko.springcourse.botapp.Update;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/")
public class BotController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping()
    public ResponseEntity<String> display(@RequestBody()Update update){
        SendMessage message = new SendMessage(update.getMessage().getChat().getChat_id(),update.getMessage().getText());
        System.out.println(message);
        System.out.println(message.getUrl());
        System.out.println(message.getToken());
        System.out.println(update.getUpdate_id());
        System.out.println(update.getMessage().getMessage_id());
        System.out.println(update.getMessage().getChat().getChat_id());
        restTemplate.postForObject(message.getUrl(),message,SendMessage.class);
        return ResponseEntity.ok().build();
    }
}
