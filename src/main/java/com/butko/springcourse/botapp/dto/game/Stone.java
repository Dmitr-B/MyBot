package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;

public class Stone extends GameState{

    public Stone(GameContext gameContext) {
        super(gameContext);
    }

    @Override
    GameResult playGame(String botData) {
        gameContext.changeState(this);
        switch (botData) {
            case "Stone":
                return GameResult.DRAW;
            //break;
            case "Scissors":
                return GameResult.WON;
            //break;
            case "Paper":
                return GameResult.LOSE;
            //break;
        }
        return null;
    }
}
