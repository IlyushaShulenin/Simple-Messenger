package ru.shulenin.simple_messanger.mapper;

import org.springframework.stereotype.Component;
import ru.shulenin.simple_messanger.dto.ChatReadDto;
import ru.shulenin.simple_messanger.dto.ChatWithUsersReadDto;
import ru.shulenin.simple_messanger.entity.Chat;

@Component
public class ChatEntityToReadDto implements Mapper<Chat, ChatReadDto> {
    @Override
    public ChatReadDto map(Chat from) {
        return new ChatReadDto(
                from.getName()
//                from.getUserChats()
//                        .stream()
//                        .map(curr -> curr.getUser().getLogin())
//                        .toList()
        );
    }
}
