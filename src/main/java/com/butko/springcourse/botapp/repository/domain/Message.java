package com.butko.springcourse.botapp.repository.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @ManyToOne(/*cascade = CascadeType.MERGE, fetch = FetchType.LAZY*/)
    @JoinColumn(name = "chat_id")
    @JsonBackReference
    private Chat chat;
}
