package ru.shulenin.music_player.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.music_player.dto.PlaylistReadDto;
import ru.shulenin.music_player.dto.PlaylistSaveDto;
import ru.shulenin.music_player.mapper.PlaylistSaveDtoToEntity;
import ru.shulenin.music_player.mapper.PlaylistToReadDto;
import ru.shulenin.music_player.repository.MusicRepository;
import ru.shulenin.music_player.repository.MusicUserRepository;
import ru.shulenin.music_player.repository.PlaylistRepository;
import ru.shulenin.music_player.service.PlaylistService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final MusicUserRepository musicUserRepository;
    private final MusicRepository musicRepository;

    private final PlaylistToReadDto playlistToReadDto;
    private final PlaylistSaveDtoToEntity playlistSaveDtoToEntity;

    @Override
    public PlaylistReadDto findById(Long id) {
        return playlistRepository.findById(id)
                .map(playlistToReadDto::map)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PlaylistReadDto> findAllByUserId(Long userId) {
        return playlistRepository.findAllByUserId(userId)
                .stream()
                .map(playlistToReadDto::map)
                .toList();
    }

    @Override
    @Transactional
    public PlaylistReadDto save(PlaylistSaveDto playlistSaveDto) {
        var user = musicUserRepository.findById(playlistSaveDto.getUserId());
        var playlist = playlistSaveDtoToEntity.map(playlistSaveDto);

        return user.map(usr -> {
            playlist.setUser(usr);
            return playlistToReadDto.map(playlistRepository.save(playlist));
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public PlaylistReadDto addMusic(Long musicId, Long playlistId) {
        var music = musicRepository.findById(musicId)
                .orElseThrow(EntityNotFoundException::new);
        var playlist = playlistRepository.findById(playlistId)
                .orElseThrow(EntityNotFoundException::new);

        playlist.addMusic(music);
        playlistRepository.save(playlist);

        return playlistToReadDto.map(playlist);
    }

    @Override
    @Transactional
    public PlaylistReadDto removeMusic(Long musicId, Long playlistId) {
        var music = musicRepository.findById(musicId)
                .orElseThrow(EntityNotFoundException::new);
        var playlist = playlistRepository.findById(playlistId)
                .orElseThrow(EntityNotFoundException::new);

        playlist.removeMusic(music);
        playlistRepository.save(playlist);

        return playlistToReadDto.map(playlist);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return playlistRepository.findById(id)
                .map(plst -> {
                    playlistRepository.delete(plst);
                    return true;
                }).orElse(false);
    }
}
