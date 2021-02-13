package com.butko.springcourse.botapp.dto;


import java.util.Objects;

public class SendMessage {
    private Integer chat_id;
    private String text;

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
