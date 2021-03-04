package com.butko.springcourse.botapp.repository.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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


    @Transient
    @ToString.Exclude
    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private List<Message> messages;
}
