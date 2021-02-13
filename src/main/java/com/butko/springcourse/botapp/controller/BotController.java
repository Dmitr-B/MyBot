package com.butko.springcourse.botapp.controller;

import com.butko.springcourse.botapp.dto.Update;
import com.butko.springcourse.botapp.service.BotService;
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

    @PostMapping
    public ResponseEntity<String> display(@RequestBody Update update) {
        System.out.println(update);
        botService.handleUpdate(update);
        return ResponseEntity.ok().build();
    }
}
