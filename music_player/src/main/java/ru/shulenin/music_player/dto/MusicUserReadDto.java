package ru.shulenin.music_player.dto;

import lombok.Value;

import java.util.List;

@Value
public class MusicUserReadDto {
    String email;
    List<String> playlists;
}
