package ru.shulenin.simple_messanger.repository.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;
import org.springframework.stereotype.Repository;
import ru.shulenin.simple_messanger.entity.Chat;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChatFetchRepository {
    private static final String USER_CHATS = "Chat.userChats";
    private static final String MESSAGES = "Chat.messages";

    private final EntityManagerFactory entityManagerFactory;

    public Optional<Chat> findByIdWithUserChats(Long id) {
        var entityManager = entityManagerFactory.createEntityManager();
        var property = getProperty(entityManager, USER_CHATS);

        return Optional.ofNullable(
                 entityManager.find(Chat.class, id, property)
        );
    }

    public Optional<Chat> findByIdWithMessages(Long id) {
        var entityManager = entityManagerFactory.createEntityManager();
        var property = getProperty(entityManager, MESSAGES);

        return Optional.ofNullable(
                entityManager.find(Chat.class, id, property)
        );
    }

    private Map<String, Object> getProperty(EntityManager entityManager, String graphName) {
        return Map.of(
                GraphSemantic.LOAD.getJakartaHintName(), entityManager.getEntityGraph(graphName)
        );
    }
}
