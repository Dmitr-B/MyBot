package com.butko.springcourse.botapp.repository;

import com.butko.springcourse.botapp.repository.domain.Chat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {
    Optional<Chat> findByChatId(long chatId);
}
