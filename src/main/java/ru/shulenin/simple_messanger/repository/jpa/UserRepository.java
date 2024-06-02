package ru.shulenin.simple_messanger.repository.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.simple_messanger.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"messages"})
    List<User> findAll();

    // boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);
}
