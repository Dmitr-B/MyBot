package com.butko.springcourse.botapp.controllers;

import com.butko.springcourse.botapp.Message;
import com.butko.springcourse.botapp.Update;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BotController {

    @PostMapping()
    public ResponseEntity<String> display(@RequestBody()Update update){
        System.out.println(update.getUpdate_id());
        System.out.println(update.getMessage().getMessage_id());
        System.out.println(update.getMessage().getChat().getChat_id());
        return ResponseEntity.ok().build();
    }
}
