package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;
import lombok.ToString;

@ToString
public class GameContext{

    private GameStrategy gameStrategy;

    public void setGameStrategy(GameStrategy gameStrategy) {
        this.gameStrategy = gameStrategy;
    }

    public void changeStrategy(GameStrategy gameStrategy) {
        this.gameStrategy = gameStrategy;
    }

    public GameResult playGame(String botData) {
        return gameStrategy.playGame(botData);
    }
}
