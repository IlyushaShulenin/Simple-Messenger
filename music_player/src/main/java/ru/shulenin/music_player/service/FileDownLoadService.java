package ru.shulenin.music_player.service;

import ru.shulenin.music_player.entity.Music;

import java.util.List;
import java.util.Optional;

public interface FileDownLoadService {
    List<Music> download(String name);
}
