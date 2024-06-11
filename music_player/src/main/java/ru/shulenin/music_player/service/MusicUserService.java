package ru.shulenin.music_player.service;

import ru.shulenin.music_player.dto.MusicUserReadDto;
import ru.shulenin.music_player.dto.MusicUserSaveDto;

import java.util.Optional;

public interface MusicUserService {
    Optional<MusicUserReadDto> findById(Long id);
    Optional<MusicUserReadDto> save(MusicUserSaveDto user);
    Optional<MusicUserReadDto> becomeCreator(Long id);
}
