package ru.shulenin.music_player.dto;

import lombok.Value;

import java.util.List;

@Value
public class PlaylistReadDto {
    String name;
    List<String> musics;
}
