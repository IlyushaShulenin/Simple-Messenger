package ru.shulenin.simple_messanger.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shulenin.simple_messanger.dto.MessageReadDto;
import ru.shulenin.simple_messanger.entity.Message;
import ru.shulenin.simple_messanger.repository.jpa.UserRepository;

@Component
@RequiredArgsConstructor
public class MessageEntityToReadDto implements Mapper<Message, MessageReadDto> {
    private final UserRepository userRepository;

    @Override
    public MessageReadDto map(Message from) {
        return new MessageReadDto(
                from.getUser().getLogin(),
                from.getChat().getName(),
                from.getSendTime(),
                from.getContent()
        );
    }
}
