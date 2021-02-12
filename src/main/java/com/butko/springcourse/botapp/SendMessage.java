package com.butko.springcourse.botapp;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@PropertySource("classpath:application.properties")
public class SendMessage {
    private Integer chat_id;
    //private Message message;
    private String url = "https://api.telegram.org/bot1663876049:AAFZn9aZfFpFHz39SLBDQcGngEmZVv60xXw/sendMessage"; //"https://api.telegram.org/bot" + getToken() + "/sendMessage";
    private String text;
    @Value("${sendMessage.token}")
    private String token;

    public SendMessage() {

    }

    public SendMessage(Integer chat_id, String text) {
        this.chat_id = chat_id;
        this.text = text;
    }

    public Integer getChat_id() {
        return chat_id;
    }

    public void setChat_id(Integer chat_id) {
        this.chat_id = chat_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public String getToken() {
        return token;
    }

    public void setToken() {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendMessage that = (SendMessage) o;
        return Objects.equals(chat_id, that.chat_id) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chat_id, text);
    }

    @Override
    public String toString() {
        return "SendMessage{" +
                "chat_id=" + chat_id +
                ", text='" + text + '\'' +
                '}';
    }
}
