package ru.shulenin.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.music_player.entity.Music;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    List<Music> findAllByName(String name);
    List<Music> findAllByCreatorId(Long id);
}
