package ru.shulenin.simple_messanger.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.simple_messanger.entity.UserChat;

public interface UserChatRepository extends JpaRepository<UserChat, Long> {
    void deleteByChatId(Long chatId);
    void deleteByUserIdAndChatId(Long userId, Long chatId);
}
