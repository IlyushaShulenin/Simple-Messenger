package ru.shulenin.simple_messanger.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.shulenin.simple_messanger.TestBase;
import ru.shulenin.simple_messanger.annotation.IntegrationTest;
import ru.shulenin.simple_messanger.dto.UserReadDto;
import ru.shulenin.simple_messanger.dto.UserSaveDto;
import ru.shulenin.simple_messanger.exception.DataRecordingException;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
@RequiredArgsConstructor
class UserServiceImplTest extends TestBase {
    private final UserService userService;

    @Test
    void findAll() {
        var users = userService.findAll();
        assertThat(users).hasSize(3);
    }

    @Test
    void findById() {
        var user = userService.findById(1L);
        assertThat(user).isPresent();
        assertThat(user.get().getId()).isEqualTo(1L);
    }

    @Test
    void saveUser() throws DataRecordingException {
        var newUser = new UserSaveDto(
            "email",
            "password",
            "login",
            "name",
            "surname"
        );

        var user = userService.saveUser(newUser);

        assertThat(user).isInstanceOf(UserReadDto.class);
        assertThat(user.getLogin()).isEqualTo(newUser.getLogin());
        assertThat(user.getName()).isEqualTo(newUser.getName());
        assertThat(user.getSurname()).isEqualTo(newUser.getSurname());
    }

    @Test
    void deleteUser() {
        var deleteResult = userService.deleteUser(1L);

        assertThat(deleteResult).isTrue();
        assertThat(userService.findById(1L)).isEmpty();
    }

    @Test
    void updateUser() {
        var editedUser = new UserSaveDto(
                "email",
                "password",
                "login",
                "name",
                "surname"
        );

        var user = userService.updateUser(1L, editedUser);
        var userFromDb = userService.findById(1L);

        assertThat(userFromDb.get().getLogin()).isEqualTo(editedUser.getLogin());
    }
}