package ru.shulenin.music_player.service;

import ru.shulenin.music_player.entity.Music;

import java.io.IOException;

public interface FileUploadService {
    void saveFile(Music file) throws IOException;
}
