package ru.shulenin.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.music_player.entity.Music;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findByName(String name);
}
