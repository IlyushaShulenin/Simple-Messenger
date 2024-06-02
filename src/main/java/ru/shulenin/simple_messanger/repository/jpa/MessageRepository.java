package ru.shulenin.simple_messanger.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.simple_messanger.entity.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByChatId(Long chatId);
}
