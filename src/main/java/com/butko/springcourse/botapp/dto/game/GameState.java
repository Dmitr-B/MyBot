package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;
import lombok.ToString;

public abstract class GameState {
    GameContext gameContext;

    public GameState(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    abstract GameResult playGame(String botData);
}
