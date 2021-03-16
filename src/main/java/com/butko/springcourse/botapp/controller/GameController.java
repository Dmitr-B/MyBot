package com.butko.springcourse.botapp.controller;

import com.butko.springcourse.botapp.repository.domain.Game;
import com.butko.springcourse.botapp.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("api/game")
public class GameController {

    private final GameService gameService;

    @GetMapping()
    public ResponseEntity<List<Game>> getAll() {
        List<Game> games = gameService.getAllGame();

        if (games.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable("id") Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        Game game = gameService.getGameById(id);

        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(game);
    }

    @PostMapping()
    public ResponseEntity<Void> insertGame(@RequestBody Game game) {

        if (game == null) {
            return ResponseEntity.badRequest().build();
        }

        this.gameService.insertNewGame(game.getId() , game.getChatId(), game.getFirstName(),
                game.getWon(), game.getDraw(), game.getLose());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable("id") long id) {
        Game game = gameService.getGameById(id);

        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        gameService.deleteGameById(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateGame(@RequestBody() Game game, @PathVariable("id") long id) {

        if (game == null) {
            return ResponseEntity.badRequest().build();
        }

        gameService.updateGameById(id, game);
        return ResponseEntity.ok().build();
    }
}
