package com.butko.springcourse.botapp.controller;

import com.butko.springcourse.botapp.dto.telegram.Update;
import com.butko.springcourse.botapp.service.BotService;
import com.butko.springcourse.botapp.service.ChatService;
import com.butko.springcourse.botapp.service.GameService;
import com.butko.springcourse.botapp.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/")
public class BotController {

    @Autowired
    BotService botService;

    @Autowired
    ChatService chatService;

    @Autowired
    MessageService messageService;


    @PostMapping
    public ResponseEntity<String> display(@RequestBody Update update) {
        log.info("Input post: " + update);
            botService.handleUpdate(update);
            chatService.sendToDB(update);
            chatService.updateDB(update);
            botService.updateCallbackQuery(update);
            messageService.messageToDB(update);
        return ResponseEntity.ok().build();
    }
}
