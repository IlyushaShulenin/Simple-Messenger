package ru.shulenin.simple_messanger.repository;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ChatRepositoryTest {
    private final ChatRepository chatRepository;

    @Test
    @Transactional(readOnly = true)
    void findAll() {
        var chatsEager = chatRepository.findAllByFirstUserId(1L);
        var chatsLazy = chatRepository.findAll();

        chatsLazy.forEach(System.out::println);

        assertThat(true).isTrue();
    }
}