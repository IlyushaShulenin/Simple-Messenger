package ru.shulenin.music_player.integration.impl.service;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.music_player.TestBase;
import ru.shulenin.music_player.annotation.IntegrationTest;
import ru.shulenin.music_player.dto.MusicUserSaveDto;
import ru.shulenin.music_player.entity.MusicUser;
import ru.shulenin.music_player.mapper.MusicUserToReadDto;
import ru.shulenin.music_player.repository.MusicUserRepository;
import ru.shulenin.music_player.service.MusicUserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@IntegrationTest
@RequiredArgsConstructor
class MusicUserServiceImplTest extends TestBase {
    private final static long TEST_USER_ID = 1L;
    private final static long TEST_USER_WRONG_ID = -1L;

    private final MusicUserService musicUserService;
    private final MusicUserRepository musicUserRepository;
    private final MusicUserToReadDto musicUserToReadDto;

   // @PersistenceContext
    private final EntityManagerFactory entityManagerFactory;

    @Test
    @Transactional(readOnly = true)
    void findById() {
        var user = musicUserService.findById(TEST_USER_ID);

        var expectedUser = musicUserRepository.getReferenceById(TEST_USER_ID);
        var expectedUserDto = musicUserToReadDto.map(expectedUser);

        assertThat(user).isEqualTo(expectedUserDto);
    }

    @Test
    void findByIdWithWrongId() {
        assertThatThrownBy(() -> musicUserService.findById(TEST_USER_WRONG_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @Transactional
    void save() {
        var newUser = new MusicUserSaveDto(
                "test@test.com",
                "test"
        );

        var savedUser = musicUserService.save(newUser);
        var expectedSaveUser = musicUserRepository.findById(4L);
        var expectedSaveUserDto = musicUserToReadDto.map(expectedSaveUser.get());

        assertThat(savedUser).isEqualTo(expectedSaveUserDto);
    }

    @Test
    @Transactional
    void becomeCreator() {
        var id = 2L;

        musicUserService.becomeCreator(id);
        var user = musicUserRepository.getReferenceById(id);

        assertThat(user.getRole()).isEqualTo(MusicUser.Role.CREATOR);
    }

    @Test
    void becomeCreatorWithWrongId() {
        assertThatThrownBy(() -> musicUserService.becomeCreator(TEST_USER_WRONG_ID))
                .isInstanceOf(EntityNotFoundException.class);
    }
}