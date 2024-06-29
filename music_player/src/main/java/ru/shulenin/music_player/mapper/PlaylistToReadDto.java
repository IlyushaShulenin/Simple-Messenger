package ru.shulenin.music_player.mapper;

import org.springframework.stereotype.Component;
import ru.shulenin.music_player.dto.PlaylistReadDto;
import ru.shulenin.music_player.entity.Music;
import ru.shulenin.music_player.entity.Playlist;

import java.util.Collections;

@Component
public class PlaylistToReadDto implements Mapper<Playlist, PlaylistReadDto> {

    @Override
    public PlaylistReadDto map(Playlist from) {
        return new PlaylistReadDto(
                from.getName(),
                from.getMusics() == null ?
                        Collections.emptyList() :
                        from.getMusics()
                                .stream()
                                .map(Music::getName)
                                .toList()
        );
    }
}
