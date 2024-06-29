package ru.shulenin.music_player.service;

import ru.shulenin.music_player.entity.Music;

import java.util.List;

public interface MusicService {
    List<Music> findAllByName(String name);
    List<Music> findAllByArtist(Long creatorId);
    Music save(Music music);
    boolean delete(Long id);
}
