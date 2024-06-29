package ru.shulenin.music_player.service;

import ru.shulenin.music_player.dto.PlaylistReadDto;
import ru.shulenin.music_player.dto.PlaylistSaveDto;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    PlaylistReadDto findById(Long id);
    List<PlaylistReadDto> findAllByUserId(Long userId);
    PlaylistReadDto save(PlaylistSaveDto playlistSaveDto);
    PlaylistReadDto addMusic(Long musicId, Long playlistId);
    PlaylistReadDto removeMusic(Long musicId, Long playlistId);
    boolean delete(Long id);
}
