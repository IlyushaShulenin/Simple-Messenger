package ru.shulenin.music_player.integration.impl.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.music_player.TestBase;
import ru.shulenin.music_player.annotation.IntegrationTest;
import ru.shulenin.music_player.repository.MusicRepository;
import ru.shulenin.music_player.service.MusicService;

import static org.assertj.core.api.Assertions.*;

@IntegrationTest
@RequiredArgsConstructor
class MusicServiceImplTest extends TestBase {
    private static final String MUSIC_NAME = "song1";
    private static final long CREATOR_ID = 1;

    private final MusicService musicService;
    private final MusicRepository musicRepository;

    @Test
    @Transactional(readOnly = true)
    void findAllByName() {
        var foundMusic = musicService.findAllByName(MUSIC_NAME);
        var expectedMusic = musicRepository.findAllByName(MUSIC_NAME);

        assertThat(foundMusic).isEqualTo(expectedMusic).isNotNull();
    }

    @Test
    void findAllByCreatorId() {
        var musics = musicService.findAllByArtist(CREATOR_ID);
        var expectedMusics = musicRepository.findAllByCreatorId(CREATOR_ID);

        assertThat(musics).isEqualTo(expectedMusics).isNotNull();
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}