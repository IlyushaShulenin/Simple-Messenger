package ru.shulenin.simple_messanger.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shulenin.simple_messanger.dto.MessageSaveDto;
import ru.shulenin.simple_messanger.entity.Message;
import ru.shulenin.simple_messanger.repository.jpa.ChatRepository;
import ru.shulenin.simple_messanger.repository.jpa.UserRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class MessageSaveDtoToEntity implements Mapper<MessageSaveDto, Message> {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Override
    public Message map(MessageSaveDto from) {
        var user = userRepository.findById(from.getSenderId());
        var chat = chatRepository.findById(from.getChatId());

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with id " + from.getSenderId() + " not found");
        }
        if (chat.isEmpty()) {
            throw new EntityNotFoundException("Chat with id " + from.getChatId() + " not found");
        }

        var message = new Message(
                user.get(),
                chat.get(),
                LocalDateTime.now(),
                from.getContent()
        );

        return message;
    }
}
