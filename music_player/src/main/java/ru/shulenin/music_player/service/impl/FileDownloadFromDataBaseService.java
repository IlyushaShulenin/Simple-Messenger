package ru.shulenin.music_player.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shulenin.music_player.entity.Music;
import ru.shulenin.music_player.repository.MusicRepository;
import ru.shulenin.music_player.service.FileDownLoadService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileDownloadFromDataBaseService implements FileDownLoadService {
    private final MusicRepository musicRepository;

    @Override
    public Optional<Music> download(String name) {
        return musicRepository.findByName(name);
    }
}
