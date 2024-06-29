package ru.shulenin.music_player.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.music_player.dto.MusicUserReadDto;
import ru.shulenin.music_player.dto.MusicUserSaveDto;
import ru.shulenin.music_player.mapper.MusicUserSaveDtoToEntity;
import ru.shulenin.music_player.mapper.MusicUserToReadDto;
import ru.shulenin.music_player.repository.MusicUserRepository;
import ru.shulenin.music_player.service.MusicUserService;

import static ru.shulenin.music_player.entity.MusicUser.Role;
import static ru.shulenin.music_player.entity.MusicUser.Role.CREATOR;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MusicUserServiceImpl implements MusicUserService {
    private final MusicUserRepository musicUserRepository;

    private final MusicUserToReadDto musicUserToReadDto;
    private final MusicUserSaveDtoToEntity musicUserSaveDtoToEntity;

    @Override
    public MusicUserReadDto findById(Long id) {
        return musicUserRepository.findById(id)
                .map(musicUserToReadDto::map)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public MusicUserReadDto save(MusicUserSaveDto user) {
        var musicUser = musicUserSaveDtoToEntity.map(user);
        var savedUser = musicUserRepository.saveAndFlush(musicUser);

        return musicUserToReadDto.map(savedUser);
    }

    @Override
    @Transactional
    public MusicUserReadDto becomeCreator(Long id) {
        var musicUser = musicUserRepository.findById(id);

        return musicUser.map(user -> {
            user.setRole(CREATOR);
            musicUserRepository.saveAndFlush(user);
            return musicUserToReadDto.map(user);
        }).orElseThrow(EntityNotFoundException::new);
    }
}
