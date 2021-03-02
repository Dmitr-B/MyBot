package com.butko.springcourse.botapp.repository.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GenericGenerator(name = "chat_seq", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_seq")
    private Long id;


    private long chatId;

    private String firstName;

    private String lastName;

    private String text;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Message> messages;
}
