package ru.shulenin.simple_messanger.repository.custom;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.simple_messanger.TestBase;
import ru.shulenin.simple_messanger.annotation.IntegrationTest;

@IntegrationTest
@RequiredArgsConstructor
@Transactional(readOnly = true)
class ChatFetchRepositoryTest extends TestBase {
    private final ChatFetchRepository chatFetchRepository;

    @Test
    void findByIdWithUserChats() {
        var chats = chatFetchRepository.findByIdWithUserChats(1L);
        chats.ifPresent(
                chat -> chat.getUserChats()
                        .forEach(System.out::println)
        );
    }

    @Test
    void findAllWithMessages() {
        var chatsWithMessages = chatFetchRepository.findByIdWithMessages(1L);
        chatsWithMessages.ifPresent(
                chat -> chat.getMessages()
                        .forEach(System.out::println)
        );
    }
}