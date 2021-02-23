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

    public void sendStatToDB(Update update) {
        //if (chatRepository.findByChatId(update.getCallbackQuery().getMessage().getChat().getId()).isEmpty()) {
            Game game = new Game();
            game.setChatId(update.getCallbackQuery().getMessage().getChat().getId());
//            chat.setFirstName(update.getMessage().getChat().getFirstName());
//            chat.setLastName(update.getMessage().getChat().getLastName());
//            chat.setText(update.getMessage().getText());
            log.info("Save data to DB: " + game);
            gameRepository.save(game);
        //} else log.info("ChatId is already defined");
    }
}
