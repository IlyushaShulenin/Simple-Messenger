package ru.shulenin.music_player.dto;

import lombok.Value;

@Value
public class PlaylistSaveDto {
    Long userId;
    String name;
}
