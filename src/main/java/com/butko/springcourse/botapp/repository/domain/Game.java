package com.butko.springcourse.botapp.repository.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GenericGenerator(name = "game_seq", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    private Long id;

    private long chatId;

    private String firstName;

    private int won;

    private int draw;

    private int lose;
}
