package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.Update;
import com.butko.springcourse.botapp.repository.ChatRepository;
import com.butko.springcourse.botapp.repository.GameRepository;
import com.butko.springcourse.botapp.repository.domain.Chat;
import com.butko.springcourse.botapp.repository.domain.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class GameService {

    private final GameRepository gameRepository;
    private final BotService botService;

    public void sendStatToDB(Update update) {
        if (gameRepository.findByChatId(update.getCallbackQuery().getMessage().getChat().getId()).isEmpty()) {
            Game game = new Game();
            game.setChatId(update.getCallbackQuery().getMessage().getChat().getId());
            game.setFirstName(update.getCallbackQuery().getFrom().getFirstName());
            switch (botService.resultGame) {
                case 1:
                    game.setWon(1);
                break;
                case 2:
                    game.setDraw(1);
                break;
                case 3:
                    game.setLose(1);
                break;
            }
            log.info("Save data to DB: " + game);
            gameRepository.save(game);
        } else updateStatInDB(update);
        log.info("ChatId is already defined");
    }

    public void updateStatInDB(Update update) {
        switch (botService.resultGame) {
            case 1:
                Game replaceWon = gameRepository.findByChatId(update.getCallbackQuery().getMessage().getChat().getId()).get();
                replaceWon.setWon(replaceWon.getWon() + 1);
                log.info("Won " + replaceWon.getWon());
                gameRepository.save(replaceWon);
                break;
            case 2:
                Game replaceDraw = gameRepository.findByChatId(update.getCallbackQuery().getMessage().getChat().getId()).get();
                replaceDraw.setDraw(replaceDraw.getDraw() + 1);
                log.info("Draw " + replaceDraw.getWon());
                gameRepository.save(replaceDraw);
                break;
            case 3:
                Game replaceLose = gameRepository.findByChatId(update.getCallbackQuery().getMessage().getChat().getId()).get();
                replaceLose.setLose(replaceLose.getLose() + 1);
                log.info("Lose " + replaceLose.getWon());
                gameRepository.save(replaceLose);
                break;
        }
    }
}
