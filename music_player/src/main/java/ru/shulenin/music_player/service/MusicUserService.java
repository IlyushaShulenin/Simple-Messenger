package ru.shulenin.music_player.service;

import ru.shulenin.music_player.dto.MusicUserReadDto;
import ru.shulenin.music_player.dto.MusicUserSaveDto;

public interface MusicUserService {
    MusicUserReadDto findById(Long id);
    MusicUserReadDto save(MusicUserSaveDto user);
    MusicUserReadDto becomeCreator(Long id);
}
