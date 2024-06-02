package ru.shulenin.simple_messanger.mapper;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.simple_messanger.TestBase;
import ru.shulenin.simple_messanger.annotation.IntegrationTest;
import ru.shulenin.simple_messanger.dto.ChatSaveDto;
import ru.shulenin.simple_messanger.repository.jpa.ChatRepository;
import ru.shulenin.simple_messanger.repository.jpa.UserRepository;

@IntegrationTest
@RequiredArgsConstructor
class ChatSaveDtoToEntityTest extends TestBase {
    private final ChatSaveDtoToEntity mapper;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    @Test
    @Transactional
    void map() {
        var dto = new ChatSaveDto(1L, "chat");
        var entity = mapper.map(dto);

        chatRepository.save(entity);
        var user = userRepository.findById(1L).get();

      //  assertThat(user.getChats()).hasSize(3).contains(entity);
    }
}