package ru.shulenin.simple_messanger.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.simple_messanger.TestBase;
import ru.shulenin.simple_messanger.annotation.IntegrationTest;
import ru.shulenin.simple_messanger.dto.ChatSaveDto;
import ru.shulenin.simple_messanger.entity.BaseEntity;
import ru.shulenin.simple_messanger.exception.DataRecordingException;
import ru.shulenin.simple_messanger.repository.jpa.ChatRepository;
import ru.shulenin.simple_messanger.repository.jpa.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@IntegrationTest
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ChatServiceImplTest extends TestBase {
    private final ChatService chatService;

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Test
    void findAll() {
        var chats = chatService.findAll();
        assertThat(chats)
                .hasSize(3);
    }

    @Test
    void findById() {
        var chat = chatService.findById(1L);
        assertThat(chat)
                .isPresent();
        assertThat(chat.get().getName())
                .isEqualTo("work");
    }

    @Test
    @Transactional
    void save() throws DataRecordingException {
        var chatDto = new ChatSaveDto(
            1L,
            "family"
        );

        var savedChat = chatService.save(chatDto);
        var user = userRepository.findById(1L);

        assertThat(savedChat.getName())
                .isEqualTo("family");
        //assertThat(getIdList(user.get().getChats()))
        //        .contains(chatDto.getUserId());
    }

    @Test
    @Transactional
    void deleteById() {
        var deleteResult = chatService.deleteById(1L);
        var chat = chatRepository.findById(1L);

        assertThat(deleteResult)
                .isTrue();
        assertThat(chat)
                .isEmpty();
    }

    @Test
    void addUser() {
        var chatId = 2L;
        var userId = 1L;

        chatService.addUser(userId, chatId);
        var chat = chatRepository.findById(chatId);
        var user = userRepository.findById(userId);

//        assertThat(getIdList(chat.get().getUsers()))
//                .contains(userId);
//        assertThat(getIdList(user.get().getChats()))
//                .contains(chatId);
    }

    @Test
    void removeUser() {
        var chatId = 3L;
        var userId = 1L;

        chatService.removeUser(userId, chatId);
        var chat = chatRepository.findById(chatId);
        var user = userRepository.findById(userId);

//        assertThat(getIdList(chat.get().getUsers()))
//                .doesNotContain(userId);
//        assertThat(getIdList(user.get().getChats()))
//                .doesNotContain(chatId);
    }

    private <T, E extends BaseEntity<T>> List<T> getIdList(List<E> entities) {
        return entities.stream()
                .map(E::getId)
                .toList();
    }
}