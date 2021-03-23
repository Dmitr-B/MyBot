package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;

public abstract class GameState {
    GameContext gameContext;

    public GameState(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    abstract GameResult playGame(String botData);
}
