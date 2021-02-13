package com.butko.springcourse.botapp.repository.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_seq")
    private Long id;


    private long chatId;

    private String firstName;

    private String lastName;

    private String text;
}
