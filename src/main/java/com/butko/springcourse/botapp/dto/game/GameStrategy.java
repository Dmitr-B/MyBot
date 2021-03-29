package com.butko.springcourse.botapp.dto.game;

import com.butko.springcourse.botapp.dto.GameResult;

public abstract class GameStrategy {

    abstract GameResult playGame(String botData);

}
