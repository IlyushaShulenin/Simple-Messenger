package ru.shulenin.simple_messanger.mapper;

import org.springframework.stereotype.Component;
import ru.shulenin.simple_messanger.dto.ChatWithMessagesReadDto;
import ru.shulenin.simple_messanger.entity.Chat;
import ru.shulenin.simple_messanger.entity.Message;

@Component
public class ChatEntityToReadWithMessagesDto implements Mapper<Chat, ChatWithMessagesReadDto> {
    @Override
    public ChatWithMessagesReadDto map(Chat from) {
        return new ChatWithMessagesReadDto(
                from.getName(),
                from.getMessages()
                        .stream()
                        .map(Message::getContent)
                        .toList()
        );
    }
}
