package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;

public abstract class GameStrategy {
    GameContext gameContext;

    public GameStrategy(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    abstract GameResult playGame(String botData);

}
