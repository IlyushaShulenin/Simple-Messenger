package ru.shulenin.music_player.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shulenin.music_player.dto.MusicUserReadDto;
import ru.shulenin.music_player.dto.MusicUserSaveDto;
import ru.shulenin.music_player.repository.MusicUserRepository;
import ru.shulenin.music_player.service.MusicUserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MusicUserServiceImpl implements MusicUserService {
    private final MusicUserRepository musicUserRepository;

    @Override
    public Optional<MusicUserReadDto> findById(Long id) {
        return musicUserRepository.findById(id).map();
    }

    @Override
    public Optional<MusicUserReadDto> save(MusicUserSaveDto user) {
        return Optional.empty();
    }

    @Override
    public Optional<MusicUserReadDto> becomeCreator(Long id) {
        return Optional.empty();
    }
}
