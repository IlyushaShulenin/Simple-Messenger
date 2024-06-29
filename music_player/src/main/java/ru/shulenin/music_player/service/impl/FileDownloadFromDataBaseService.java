package ru.shulenin.music_player.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shulenin.music_player.entity.Music;
import ru.shulenin.music_player.repository.MusicRepository;
import ru.shulenin.music_player.service.FileDownLoadService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileDownloadFromDataBaseService implements FileDownLoadService {
    private final MusicRepository musicRepository;

    @Override
    public List<Music> download(String name) {
        return musicRepository.findAllByName(name);
    }
}
