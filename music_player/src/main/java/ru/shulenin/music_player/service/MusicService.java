package ru.shulenin.music_player.service;

import ru.shulenin.music_player.entity.Music;

import java.util.List;
import java.util.Optional;

public interface MusicService {
    List<Music> findAllByName(String name);
    Music save(Music music);
    Music delete(Long id);
}
