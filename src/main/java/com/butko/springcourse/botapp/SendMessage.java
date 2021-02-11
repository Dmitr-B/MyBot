package com.butko.springcourse.botapp;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class SendMessage {
    private Integer chat_id;
    private Message message;
    private String text;
    @Value("${sendMessage.token}")
    private String token;

    public SendMessage(Integer chat_id, Message message, String text) {
        this.chat_id = chat_id;
        this.message = message;
        this.text = text;
    }

    public String getToken() {
        return token;
    }

    public Integer getChat_id() {
        return chat_id;
    }

    public void setChat_id(Integer chat_id) {
        this.chat_id = chat_id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
