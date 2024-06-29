package ru.shulenin.music_player.mapper;

import org.springframework.stereotype.Component;
import ru.shulenin.music_player.dto.PlaylistSaveDto;
import ru.shulenin.music_player.entity.Playlist;

@Component
public class PlaylistSaveDtoToEntity implements Mapper<PlaylistSaveDto, Playlist> {
    @Override
    public Playlist map(PlaylistSaveDto from) {
        return new Playlist(
                from.getName(),
                null
        );
    }
}
