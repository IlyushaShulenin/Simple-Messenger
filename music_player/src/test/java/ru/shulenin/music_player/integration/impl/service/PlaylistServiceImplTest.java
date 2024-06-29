package ru.shulenin.music_player.integration.impl.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.shulenin.music_player.TestBase;
import ru.shulenin.music_player.annotation.IntegrationTest;
import ru.shulenin.music_player.dto.PlaylistReadDto;
import ru.shulenin.music_player.dto.PlaylistSaveDto;
import ru.shulenin.music_player.service.PlaylistService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@IntegrationTest
@RequiredArgsConstructor
class PlaylistServiceImplTest extends TestBase {
    private static final long PLAYLIST_ID = 1L;
    private static final long PLAYLIST_WRONG_ID = -1L;
    private static final long MUSIC_ID = 1L;
    private static final long MUSIC_WRONG_ID = -1L;
    private static final long USER_ID = 1L;

    private final PlaylistService playlistService;

    @Test
    void findById() {
        var playlist = playlistService.findById(PLAYLIST_ID);
        var expectedPlaylist = new PlaylistReadDto(
                "Training",
                List.of("song1", "song2", "song3")
        );

        assertThat(playlist).isEqualTo(expectedPlaylist);
    }

    @Test
    void findNonExistentPlaylist() {
        assertThatThrownBy(() -> playlistService.findById(PLAYLIST_WRONG_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findAllByUserId() {
        var playlists = playlistService.findAllByUserId(USER_ID);
        var expectedPlaylist = new PlaylistReadDto(
                "Training",
                List.of("song1", "song2", "song3")
        );

        assertThat(playlists).hasSize(3).contains(expectedPlaylist);
    }

    @Test
    void save() {
        var playlistDto = new PlaylistSaveDto(
                1L,
                "test"
        );

        var id = 10L;
        var playlist = playlistService.save(playlistDto);
        var expectedPlaylist = playlistService.findById(id);

        assertThat(playlist).isEqualTo(expectedPlaylist);
    }

    @Test
    void addMusic() {
        var musicId = 5L;
        var playlist = playlistService.addMusic(musicId, PLAYLIST_ID);

        assertThat(playlist.getMusics()).hasSize(4);
    }

    @Test
    void addMusicToNonExistingPlaylist() {
        assertThatThrownBy(() -> playlistService.addMusic(MUSIC_ID, PLAYLIST_WRONG_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void addNonExistingMusic() {
        assertThatThrownBy(() -> playlistService.addMusic(MUSIC_WRONG_ID, PLAYLIST_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void removeMusic() {
        var musicId = 1L;
        var playlist = playlistService.removeMusic(musicId, PLAYLIST_ID);

        assertThat(playlist.getMusics()).hasSize(2);
    }

    @Test
    void removeMusicFromNonExistingPlaylist() {
        assertThatThrownBy(() -> playlistService.removeMusic(MUSIC_ID, PLAYLIST_WRONG_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void removeMusicWithWrongId() {
        assertThatThrownBy(() -> playlistService.removeMusic(MUSIC_WRONG_ID, PLAYLIST_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void delete() {
        var isDeleted = playlistService.delete(PLAYLIST_ID);
        assertThat(isDeleted).isTrue();
    }

    @Test
    void deleteWithCheckContaining() {
        playlistService.delete(PLAYLIST_ID);
        assertThatThrownBy(() -> playlistService.findById(PLAYLIST_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }
}