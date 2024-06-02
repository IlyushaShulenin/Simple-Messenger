package ru.shulenin.simple_messanger.mapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.shulenin.simple_messanger.dto.ChatSaveDto;
import ru.shulenin.simple_messanger.entity.Chat;
import ru.shulenin.simple_messanger.repository.jpa.UserRepository;

@Component
@RequiredArgsConstructor
public class ChatSaveDtoToEntity implements Mapper<ChatSaveDto, Chat> {
    private final UserRepository userRepository;

    @Override
    public Chat map(ChatSaveDto from) throws EntityNotFoundException {
        var user = userRepository.findById(from.getUserId());

        if (user.isEmpty()) {
            throw new EntityNotFoundException("User with id " + from.getUserId() + " not found");
        }

        var chat = new Chat(from.getName());
      //  user.get().addChat(chat);

        return chat;
    }
}
