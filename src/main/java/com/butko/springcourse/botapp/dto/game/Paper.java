package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;

public class Paper extends GameState{

    public Paper(GameContext gameContext) {
        super(gameContext);
    }

    @Override
    GameResult playGame(String botData) {
        gameContext.changeState(this);
        switch (botData) {
            case "Stone":
                return GameResult.WON;
            //break;
            case "Scissors":
                return GameResult.LOSE;
            //break;
            case "Paper":
                return GameResult.DRAW;
            //break;
        }
        return null;
    }
}
