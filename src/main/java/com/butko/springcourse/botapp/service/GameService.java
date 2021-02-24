package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.GameResult;
import com.butko.springcourse.botapp.repository.GameRepository;
import com.butko.springcourse.botapp.repository.domain.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class GameService {

    private final GameRepository gameRepository;
    //private final BotService botService;

    public void sendStatToDB(GameResult gameResult, Integer chatId) {
        if (gameRepository.existsById(Long.valueOf(chatId))) {
            Game game = new Game();
            game.setChatId(chatId);
//            game.setFirstName(update.getCallbackQuery().getFrom().getFirstName());
            switch (gameResult) {
                case WON:
                    game.setWon(1);
                break;
                case DRAW:
                    game.setDraw(1);
                break;
                case LOSE:
                    game.setLose(1);
                break;
            }
            log.info("Save data to DB: " + game);
            gameRepository.save(game);
        } else updateStatInDB(gameResult, chatId);
        log.info("ChatId is already defined");
    }

    public void updateStatInDB(GameResult gameResult, Integer chatId) {
        Game replaceResult = gameRepository.findByChatId(chatId).get();
        switch (gameResult) {
            case WON:
                //log.info("Won " + replaceWon.getWon());
                replaceResult.setWon(replaceResult.getWon() + 1);
                gameRepository.save(replaceResult);
                break;
            case DRAW:
                //Game replaceDraw = gameRepository.findByChatId(update.getCallbackQuery().getMessage().getChat().getId()).get();
                //log.info("Draw " + replaceDraw.getDraw());
                replaceResult.setDraw(replaceResult.getDraw() + 1);
                gameRepository.save(replaceResult);
                break;
            case LOSE:
                //Game replaceLose = gameRepository.findByChatId(update.getCallbackQuery().getMessage().getChat().getId()).get();
                //log.info("Lose " + replaceLose.getLose());
                replaceResult.setLose(replaceResult.getLose() + 1);
                gameRepository.save(replaceResult);
                break;
        }
    }
}
