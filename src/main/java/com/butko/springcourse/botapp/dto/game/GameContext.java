package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;

public class GameContext{

    private GameState gameState;
    //private String botData;

    /*public GameContext(GameState gameState*//*, String botData*//*) {
        this.gameState = new Stone(this)*//*gameState*//*;
        //this.botData = botData;
    }*/

    public GameContext() {
        this.gameState = null;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void changeState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameResult playGame(String botData) {
        return gameState.playGame(botData);
    }
}
