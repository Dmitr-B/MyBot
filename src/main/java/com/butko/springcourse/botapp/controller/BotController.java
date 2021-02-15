package com.butko.springcourse.botapp.controller;

import com.butko.springcourse.botapp.dto.Update;
import com.butko.springcourse.botapp.service.BotService;
import com.butko.springcourse.botapp.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BotController {

    @Autowired
    BotService botService;

    @Autowired
    ChatService chatService;

    @PostMapping
    public ResponseEntity<String> display(@RequestBody Update update) {
        botService.handleUpdate(update);
        chatService.sendToDB(update);
        return ResponseEntity.ok().build();
    }
}
