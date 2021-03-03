package com.butko.springcourse.botapp.controller;

import com.butko.springcourse.botapp.repository.domain.Chat;
import com.butko.springcourse.botapp.repository.domain.Message;
import com.butko.springcourse.botapp.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("api/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Message message = messageService.getById(id);
        //log.info("MSG" + message);

        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(message);
    }

    @GetMapping()
    public ResponseEntity<List<Message>> getAll(){
        List<Message> messages = messageService.getAll();

        if (messages.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(messages);
    }

    @PostMapping()
    public ResponseEntity<Message> save(@RequestBody Message message) {

        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        messageService.saveMessage(message);
        return ResponseEntity.ok(message);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Message> update(@RequestBody Message message, @PathVariable("id") Long id) {

        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        messageService.updateMessage(id, message);
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable("id") Long id) {
        Message message = messageService.getById(id);

        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
