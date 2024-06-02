package ru.shulenin.simple_messanger.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.simple_messanger.TestBase;
import ru.shulenin.simple_messanger.annotation.IntegrationTest;
import ru.shulenin.simple_messanger.dto.MessageReadDto;
import ru.shulenin.simple_messanger.dto.MessageSaveDto;
import ru.shulenin.simple_messanger.exception.DataFetchingException;
import ru.shulenin.simple_messanger.exception.DataRecordingException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
@RequiredArgsConstructor
@Transactional
class MessageServiceImplTest extends TestBase {
    private final MessageService messageService;

    @Test
    void findAll() throws DataFetchingException {
        var messages = messageService.findAll(1L);
        assertThat(messages)
                .hasSize(3);
    }

    @Test
    void findById() {
        var message = messageService.findById(1L);
        assertThat(message)
                .isPresent();
        assertThat(message.get().getSenderLogin())
                .isEqualTo("123");
        assertThat(message.get().getChatName())
                .isEqualTo("work");
    }

    @Test
    @Transactional
    void save() throws DataRecordingException {
        var messageDto = new MessageSaveDto(
                1L,
                1L,
                "hello"
        );
        var expectedMessage = new MessageReadDto(
                "123",
                "work",
                LocalDateTime.now(),
                "hello"
        );

        var message = messageService.save(messageDto);

        assertThat(message.getSenderLogin()).isEqualTo(expectedMessage.getSenderLogin());
        assertThat(message.getChatName()).isEqualTo(expectedMessage.getChatName());
        assertThat(message.getContent()).isEqualTo(expectedMessage.getContent());
    }

    @Test
    void delete() {
        var result = messageService.delete(1L);
        var message = messageService.findById(1L);

        assertThat(result).isTrue();
        assertThat(message).isEmpty();
    }
}