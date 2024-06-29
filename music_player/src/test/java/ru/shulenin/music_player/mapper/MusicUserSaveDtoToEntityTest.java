package ru.shulenin.music_player.mapper;

import org.junit.jupiter.api.Test;
import ru.shulenin.music_player.dto.MusicUserSaveDto;
import ru.shulenin.music_player.entity.MusicUser;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.shulenin.music_player.entity.MusicUser.Role.LISTENER;

class MusicUserSaveDtoToEntityTest {

    @Test
    void map() {
        var mapper = new MusicUserSaveDtoToEntity();
        var dto = new MusicUserSaveDto(
                "test@mail.com",
                "test"
        );
        var expectedEntity = new MusicUser().builder()
                .email("test@mail.com")
                .password("test")
                .playlists(Collections.emptyList())
                .role(LISTENER)
                .build();

        var entity = mapper.map(dto);

        assertThat(entity).isEqualTo(expectedEntity).isNotNull();
    }
}