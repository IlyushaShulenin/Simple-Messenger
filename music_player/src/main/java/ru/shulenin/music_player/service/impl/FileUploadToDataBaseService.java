package ru.shulenin.music_player.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.shulenin.music_player.entity.Music;
import ru.shulenin.music_player.entity.MusicUser;
import ru.shulenin.music_player.repository.MusicRepository;
import ru.shulenin.music_player.service.FileUploadService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileUploadToDataBaseService implements FileUploadService {
    private final MusicRepository musicRepository;

    @Override
    public void saveFile(MultipartFile file) throws IOException {
        var doc = new Music(file.getOriginalFilename(),
                new MusicUser(),
                file.getBytes());
        musicRepository.save(doc);
    }
}
