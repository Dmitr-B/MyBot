package com.butko.springcourse.botapp.repository;

import com.butko.springcourse.botapp.repository.domain.Chat;
import com.butko.springcourse.botapp.repository.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findByChatId(long chatId);
}