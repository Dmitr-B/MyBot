package com.butko.springcourse.botapp.controller;

import com.butko.springcourse.botapp.dto.Update;
import com.butko.springcourse.botapp.service.BotService;
import com.butko.springcourse.botapp.service.ChatService;
import com.butko.springcourse.botapp.service.GameService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Log4j2
@Controller
@RequestMapping("/")
public class BotController {

    @Autowired
    BotService botService;

    @Autowired
    ChatService chatService;

    @Autowired
    GameService gameService;

    @PostMapping
    public ResponseEntity<String> display(@RequestBody Update update) {
        log.info("Input post: " + update);
        if (update.hasMessage()) {
            botService.handleUpdate(update);
            chatService.sendToDB(update);
        } else if (update.hasEditedMessage()) {
            chatService.updateDB(update);
        }
        if (update.hasCallbackQuery()) {
            botService.updateCallbackQuery(update);
            gameService.sendStatToDB(update);
        }
        return ResponseEntity.ok().build();
    }
}
