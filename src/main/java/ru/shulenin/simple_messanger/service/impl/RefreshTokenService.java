package ru.shulenin.simple_messanger.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shulenin.simple_messanger.entity.RefreshToken;
import ru.shulenin.simple_messanger.repository.jpa.RefreshTokenRepository;
import ru.shulenin.simple_messanger.repository.jpa.UserRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshToken createRefreshToken(String username) {
        var refreshToken = RefreshToken.builder()
                .user(userRepository.findByLogin(username).get())
                .token(UUID.randomUUID().toString())
                .expryTime(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyRefreshToken(RefreshToken token) {
        if (token.getExpryTime().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
        }
        return token;
    }
}
