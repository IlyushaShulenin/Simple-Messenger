package ru.shulenin.simple_messanger.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.simple_messanger.entity.Chat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"firstUser", "secondUser", "messages"})
    List<Chat> findAllByFirstUserId(Long id);
}
