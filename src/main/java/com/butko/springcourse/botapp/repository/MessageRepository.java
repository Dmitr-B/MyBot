package com.butko.springcourse.botapp.repository;

import com.butko.springcourse.botapp.repository.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository  extends CrudRepository<Message, Long> {
}
