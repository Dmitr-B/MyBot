package com.butko.springcourse.botapp.repository;

import com.butko.springcourse.botapp.repository.domain.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findAll();

    Optional<Message> findByMessageId(int messageId);

    @Query("select m from Message m join Chat c on m.chat.id = c.id and c.id = ?1")
    List<Message> findMessageByChatId(long chatId);
}
