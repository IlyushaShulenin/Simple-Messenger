package ru.shulenin.simple_messanger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.simple_messanger.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
