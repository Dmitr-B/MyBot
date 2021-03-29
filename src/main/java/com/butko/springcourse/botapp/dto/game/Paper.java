package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;

public class Paper extends GameStrategy{

    @Override
    GameResult playGame(String botData) {
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
