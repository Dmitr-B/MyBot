package com.butko.springcourse.botapp.repository;

import com.butko.springcourse.botapp.repository.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}
