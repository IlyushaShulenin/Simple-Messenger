package ru.shulenin.music_player.mapper;

import org.junit.jupiter.api.Test;
import ru.shulenin.music_player.dto.MusicUserReadDto;
import ru.shulenin.music_player.entity.MusicUser;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class MusicUserToReadDtoTest {

    @Test
    void map() {
        var mapper = new MusicUserToReadDto();
        var entity = new MusicUser().builder()
                .email("test@gmail.com")
                .build();

        var expectedDto = new MusicUserReadDto(
                "test@gmail.com",
                Collections.emptyList()
        );
        var dto = mapper.map(entity);

        assertThat(dto).isEqualTo(expectedDto);
    }
}