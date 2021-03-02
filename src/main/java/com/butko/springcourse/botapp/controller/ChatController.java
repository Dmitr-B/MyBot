package com.butko.springcourse.botapp.controller;

import com.butko.springcourse.botapp.repository.domain.Chat;
import com.butko.springcourse.botapp.service.ChatService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChat(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }

        Chat chat = chatService.getById(id);

        if (chat == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(chat);

    }

    @GetMapping()
    public ResponseEntity<List<Chat>> getAll(){
        List<Chat> chats = chatService.getAll();

        if (chats.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(chats);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateChat(@RequestBody Chat chat, @PathVariable("id") Long id) {

        if (chat == null) {
            return ResponseEntity.notFound().build();
        }

            chatService.update(id,chat);
        return ResponseEntity.ok().build();
    }

    //1. створити таблицю з ключом на таблицю chat
    //2. виборка даних з 2 таблиць

    //todo створити table message де будуть зберігатись всі повідомлення
    // - id
    // - msgId
    // - text

    // @ManyToOne
    // - Chat chat

    @PostMapping()
    public ResponseEntity<Chat> saveChat(@RequestBody Chat chat) {

        if (chat == null) {
            return ResponseEntity.notFound().build();
        }

        this.chatService.saveChat(chat);
        return ResponseEntity.status(HttpStatus.CREATED).body(chat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable("id") Long id) {
        Chat chat = chatService.getById(id);

        if (chat == null) {
            return ResponseEntity.notFound().build();
        }

        chatService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
