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

    public void sendStatToDB(GameResult gameResult, Integer chatId, String firstName) {
        if (gameRepository.findByChatId(Long.valueOf(chatId)).isEmpty()) {
            Game game = new Game();
            game.setChatId(chatId);
            game.setFirstName(firstName);
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
                replaceResult.setWon(replaceResult.getWon() + 1);
                break;
            case DRAW:
                replaceResult.setDraw(replaceResult.getDraw() + 1);
                break;
            case LOSE:
                replaceResult.setLose(replaceResult.getLose() + 1);
                break;
        }
        gameRepository.save(replaceResult);
    }

    public String showStat(Integer chatId) {
        Game showResult = gameRepository.findByChatId((chatId)).get();
        StringBuilder result = new StringBuilder("Пользователь: ");
        result.append(showResult.getFirstName());
        result.append("\nПобеды: ");
        result.append(showResult.getWon());
        result.append("\nНичьи: ");
        result.append(showResult.getDraw());
        result.append("\nПоражения: ");
        result.append(showResult.getLose());
        /*return String.format("Пользователь: " + showResult.getFirstName() + "%nПобеды: "
        + showResult.getWon() + "%nНичьи: " + showResult.getDraw() + "%nПоражения: " + showResult.getLose());*/
        return result.toString();
    }
}
