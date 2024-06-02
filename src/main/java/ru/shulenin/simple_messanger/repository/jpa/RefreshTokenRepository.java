package ru.shulenin.simple_messanger.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.simple_messanger.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
}
