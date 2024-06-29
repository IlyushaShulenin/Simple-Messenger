package ru.shulenin.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.music_player.entity.Playlist;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findAllByUserId(long userId);
}
