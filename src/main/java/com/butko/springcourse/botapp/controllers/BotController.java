package com.butko.springcourse.botapp.controllers;

import com.butko.springcourse.botapp.Update;
import org.apache.coyote.Response;
import org.apache.logging.log4j.message.Message;
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
        //System.out.println(update.getMessage());
        System.out.println(ResponseEntity.ok().build());
        return ResponseEntity.ok().build();
    }
}
