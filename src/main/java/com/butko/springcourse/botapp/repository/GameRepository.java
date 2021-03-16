package com.butko.springcourse.botapp.repository;

import com.butko.springcourse.botapp.repository.domain.Game;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    Optional<Game> findByChatId(long chatId);

    @Query(value = "select * from game g order by g.id",
            nativeQuery = true)
    List<Game> findAllGame();

    @Query("select g from Game g where g.id = :id")
    Game findGameById(@Param("id") Long id);

    //@Transactional
    @Modifying
    @Query(value = "insert into game(id, chat_id, first_name, won, draw, lose) values (:id, :chatId, :firstName, :won, :draw, :lose)",
    nativeQuery = true)
    void insertGame(@Param("id") long id, @Param("chatId") long chatId, @Param("firstName") String firstName,
                    @Param("won") int won, @Param("draw") int draw, @Param("lose") int lose);

    @Modifying
    @Query("delete from Game g where g.id = ?1")
    void deleteGame(long id);

    @Modifying
    @Query(value = "update game set id = :id, chat_id = :chatId, first_name = :firstName, " +
            "won = :won, draw = :draw, lose = :lose where id = :id",
    nativeQuery = true)
    void updateGame(@Param("id") long id, @Param("chatId") long chatId, @Param("firstName") String firstName,
                       @Param("won") int won, @Param("draw") int draw, @Param("lose") int lose);
}
