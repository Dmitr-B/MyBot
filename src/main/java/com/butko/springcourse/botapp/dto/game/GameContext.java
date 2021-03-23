package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;
import lombok.ToString;

@ToString
public class GameContext{

    private GameState gameState;

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void changeState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameResult playGame(String botData) {
        return gameState.playGame(botData);
    }
}
