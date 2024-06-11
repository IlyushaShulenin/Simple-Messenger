package ru.shulenin.music_player.mapper;

import ru.shulenin.music_player.dto.MusicUserReadDto;
import ru.shulenin.music_player.entity.MusicUser;

public class MusicUserToReadDto implements Mapper<MusicUser, MusicUserReadDto> {
    @Override
    public MusicUserReadDto map(MusicUser from) {
        return null;
    }
}
