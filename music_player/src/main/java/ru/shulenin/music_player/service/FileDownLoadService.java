package ru.shulenin.music_player.service;

import ru.shulenin.music_player.entity.Music;

import java.util.Optional;

public interface FileDownLoadService {
    Optional<Music> download(String name);
}
