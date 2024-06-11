package ru.shulenin.music_player.service;

import ru.shulenin.music_player.dto.PlaylistReadDto;

import java.util.Optional;

public interface PlaylistService {
    Optional<PlaylistReadDto> findById(Long id);

}
