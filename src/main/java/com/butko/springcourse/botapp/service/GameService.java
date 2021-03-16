package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.GameResult;
import com.butko.springcourse.botapp.repository.GameRepository;
import com.butko.springcourse.botapp.repository.domain.Game;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        Game updateResult = gameRepository.findByChatId(chatId).orElseThrow();
        switch (gameResult) {
            case WON:
                updateResult.setWon(updateResult.getWon() + 1);
                break;
            case DRAW:
                updateResult.setDraw(updateResult.getDraw() + 1);
                break;
            case LOSE:
                updateResult.setLose(updateResult.getLose() + 1);
                break;
        }
        gameRepository.save(updateResult);
    }

    public String showStat(Integer chatId) {
        Game showResult = gameRepository.findByChatId((chatId)).orElseThrow();

        StringBuilder result = new StringBuilder("Пользователь: ");
        result.append(showResult.getFirstName());
        result.append("\nПобеды: ");
        result.append(showResult.getWon());
        result.append("\nНичьи: ");
        result.append(showResult.getDraw());
        result.append("\nПоражения: ");
        result.append(showResult.getLose());
        return result.toString();
    }

    public List<Game> getAllGame() {
        return gameRepository.findAllGame();
    }

    public Game getGameById(Long id) {
        return gameRepository.findGameById(id);
    }

    @Transactional
    public void insertNewGame(long id, long chatId, String firstName, int won, int draw, int lose){
        gameRepository.insertGame(id, chatId, firstName, won, draw, lose);
    }

    @Transactional
    public void deleteGameById(long id) {
        gameRepository.deleteGame(id);
    }

    @Transactional
    public void updateGameById(long id, Game updateGame) {
        gameRepository.updateGame(id, updateGame.getChatId(), updateGame.getFirstName(), updateGame.getWon(),
                updateGame.getDraw(), updateGame.getLose());
    }
}
