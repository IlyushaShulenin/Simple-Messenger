package ru.shulenin.simple_messanger.mapper;

import org.springframework.stereotype.Component;
import ru.shulenin.simple_messanger.dto.ChatWithUsersReadDto;
import ru.shulenin.simple_messanger.entity.Chat;

@Component
public class ChatEntityToReadWithUsersDto implements Mapper<Chat, ChatWithUsersReadDto> {
    @Override
    public ChatWithUsersReadDto map(Chat from) {
        return new ChatWithUsersReadDto(
                from.getName(),
                from.getUserChats()
                        .stream()
                        .map(userChat -> userChat.getUser().getLogin())
                        .toList()
        );
    }
}
