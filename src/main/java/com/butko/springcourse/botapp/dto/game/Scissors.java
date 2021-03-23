package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;

public class Scissors extends GameState{

    public Scissors(GameContext gameContext) {
        super(gameContext);
    }

    @Override
    GameResult playGame(String botData) {
        gameContext.changeState(this);
        switch (botData) {
            case "Stone":
                return GameResult.LOSE;
            //break;
            case "Scissors":
                return GameResult.DRAW;
            //break;
            case "Paper":
                return GameResult.WON;
            //break;
        }
        return null;
    }
}
