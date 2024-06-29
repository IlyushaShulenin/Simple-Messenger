package ru.shulenin.music_player.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.music_player.entity.Music;
import ru.shulenin.music_player.repository.MusicRepository;
import ru.shulenin.music_player.service.MusicService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MusicServiceImpl implements MusicService {
    private final MusicRepository musicRepository;

    @Override
    public List<Music> findAllByName(String name) {
        return musicRepository.findAllByName(name);
    }

    @Override
    public List<Music> findAllByArtist(Long creatorId) {
        return musicRepository.findAllByCreatorId(creatorId);
    }

    @Override
    @Transactional
    public Music save(Music music) {
        return musicRepository.save(music);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        var music = musicRepository.findById(id);

        return music.map(msc -> {
            musicRepository.delete(msc);
            return true;
        }).orElse(false);
    }
}
