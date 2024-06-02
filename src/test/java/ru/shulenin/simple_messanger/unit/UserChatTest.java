package ru.shulenin.simple_messanger.unit;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.simple_messanger.TestBase;
import ru.shulenin.simple_messanger.annotation.IntegrationTest;
import ru.shulenin.simple_messanger.entity.Chat;
import ru.shulenin.simple_messanger.repository.jpa.ChatRepository;
import ru.shulenin.simple_messanger.repository.jpa.UserRepository;

@IntegrationTest
@RequiredArgsConstructor
public class UserChatTest extends TestBase {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Test
    @Transactional
    public void addUserAndChat() {
        var user1 = userRepository.findById(1L).get();
        var chat = new Chat(
            "chat"
        );

       // user1.addChat(chat);

        chatRepository.save(chat);

        var user2 = userRepository.findById(1L).get();
    }

}
