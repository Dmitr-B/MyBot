package com.butko.springcourse.botapp.repository.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GenericGenerator(name = "msg_seq", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
    private Long id;

    private int messageId;

    private String text;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;
}
