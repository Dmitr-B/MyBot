package com.butko.springcourse.botapp.service;

import com.butko.springcourse.botapp.dto.GameResult;
import com.butko.springcourse.botapp.repository.GameRepository;
import com.butko.springcourse.botapp.repository.domain.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameServiceUnitTest {

    private static final long TEST_ID = 1;
    private static final int TEST_INT_ID = 1;
    private static final String TEST_FIRST_NAME = "test user";
    private static final String TEST_TEXT = "test";

    @InjectMocks
    private GameService gameService;
    @Mock
    private GameRepository gameRepository;

    @Test
    void sendStatToDB_whenHasNotChatId_thenSave() {
        Game game = new Game();
        GameResult gameResult = GameResult.WON;
        game.setChatId(TEST_ID);
        game.setWon(1);
        game.setFirstName(TEST_FIRST_NAME);

        when(gameRepository.findByChatId(TEST_ID)).thenReturn(Optional.empty());

        gameService.sendStatToDB(gameResult, TEST_INT_ID, TEST_FIRST_NAME);

        verify(gameRepository).findByChatId(TEST_ID);
        verify(gameRepository).save(game);
    }

    @Test
    void updateStatInDB_whenHasChatId_thenUpdate() {
        Game game = new Game();
        GameResult gameResult = GameResult.DRAW;
        game.setChatId(TEST_ID);
        game.setWon(1);
        game.setFirstName(TEST_FIRST_NAME);

        when(gameRepository.findByChatId(TEST_ID)).thenReturn(Optional.of(game));

        gameService.updateStatInDB(gameResult,TEST_INT_ID);

        verify(gameRepository).findByChatId(TEST_ID);
        verify(gameRepository).save(game);
    }

    @Test
    void showStat_whenHasChatId_thenShow() {
        Game game = new Game();
        game.setChatId(TEST_ID);
        game.setWon(1);
        game.setFirstName(TEST_FIRST_NAME);
        String expected = "Пользователь: " + TEST_FIRST_NAME + "\nПобеды: " + game.getWon() + "\nНичьи: " +
                game.getDraw() + "\nПоражения: " + game.getLose();

        when(gameRepository.findByChatId(TEST_ID)).thenReturn(Optional.of(game));

        String actual = gameService.showStat(TEST_INT_ID);
        assertEquals(expected, actual);

        verify(gameRepository).findByChatId(TEST_ID);
    }

    @Test
    void  showStat_whenHasNotChatId_thenThrow() {
        String expectedMessage = "test_throw";

        when(gameRepository.findByChatId(TEST_ID).isEmpty()).thenThrow(new NoSuchElementException(expectedMessage));

        NoSuchElementException actual = assertThrows(NoSuchElementException.class,
                () -> gameService.showStat(TEST_INT_ID));

        assertEquals(expectedMessage, actual.getMessage());

        verify(gameRepository).findByChatId(TEST_ID);
    }
}