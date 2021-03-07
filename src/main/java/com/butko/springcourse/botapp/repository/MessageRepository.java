package com.butko.springcourse.botapp.repository;

import com.butko.springcourse.botapp.repository.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository  extends CrudRepository<Message, Long> {
    List<Message> findAll();
    Message findByMessageId(int messageId);
}
