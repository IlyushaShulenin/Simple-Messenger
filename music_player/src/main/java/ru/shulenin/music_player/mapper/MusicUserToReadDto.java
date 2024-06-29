package ru.shulenin.music_player.mapper;

import org.springframework.stereotype.Component;
import ru.shulenin.music_player.dto.MusicUserReadDto;
import ru.shulenin.music_player.entity.MusicUser;
import ru.shulenin.music_player.entity.Playlist;

import java.util.Collections;

@Component
public class MusicUserToReadDto implements Mapper<MusicUser, MusicUserReadDto> {
    @Override
    public MusicUserReadDto map(MusicUser from) {
        return new MusicUserReadDto(
                from.getEmail(),
                from.getPlaylists() == null ?
                        Collections.emptyList() :
                        from.getPlaylists()
                            .stream()
                            .map(Playlist::toString)
                            .toList()
        );
    }
}
