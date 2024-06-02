package ru.shulenin.simple_messanger.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.simple_messanger.dto.ChatReadDto;
import ru.shulenin.simple_messanger.dto.ChatWithMessagesReadDto;
import ru.shulenin.simple_messanger.dto.ChatWithUsersReadDto;
import ru.shulenin.simple_messanger.dto.ChatSaveDto;
import ru.shulenin.simple_messanger.entity.UserChat;
import ru.shulenin.simple_messanger.exception.DataRecordingException;
import ru.shulenin.simple_messanger.mapper.ChatEntityToReadDto;
import ru.shulenin.simple_messanger.mapper.ChatEntityToReadWithMessagesDto;
import ru.shulenin.simple_messanger.mapper.ChatEntityToReadWithUsersDto;
import ru.shulenin.simple_messanger.mapper.ChatSaveDtoToEntity;
import ru.shulenin.simple_messanger.repository.custom.ChatFetchRepository;
import ru.shulenin.simple_messanger.repository.jpa.ChatRepository;
import ru.shulenin.simple_messanger.repository.jpa.UserChatRepository;
import ru.shulenin.simple_messanger.repository.jpa.UserRepository;
import ru.shulenin.simple_messanger.service.ChatService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final UserChatRepository userChatRepository;

    private final ChatFetchRepository chatFetchRepository;

    private final ChatEntityToReadDto chatEntityToReadDto;
    private final ChatEntityToReadWithUsersDto chatEntityToReadWithUsersDto;
    private final ChatEntityToReadWithMessagesDto chatEntityToReadWithMessagesDto;
    private final ChatSaveDtoToEntity chatSaveDtoToEntity;

    @Override
    public List<ChatReadDto> findAll() {
        return chatRepository.findAll()
                .stream()
                .map(chatEntityToReadDto::map)
                .toList();
    }

    @Override
    public Optional<ChatReadDto> findById(Long id) {
        return chatRepository.findById(id)
                .map(chatEntityToReadDto::map);
    }


    @Override
    public Optional<ChatWithUsersReadDto> findByIdWithUsers(Long id) {
        return chatFetchRepository.findByIdWithUserChats(id)
                .map(chatEntityToReadWithUsersDto::map);
    }

    @Override
    public Optional<ChatWithMessagesReadDto> findByIdWithMessages(Long id) {
        return chatFetchRepository.findByIdWithMessages(id)
                .map(chatEntityToReadWithMessagesDto::map);
    }

    @Override
    @Transactional
    public ChatReadDto save(ChatSaveDto chatDto) throws DataRecordingException {
        try {
            var chat = chatSaveDtoToEntity.map(chatDto);
            var user = userRepository.findById(chatDto.getUserId());

            var userChat = new UserChat(user.get(), chat);
            userChatRepository.save(userChat);
            //chatRepository.save(chat);

            return chatEntityToReadDto.map(chat);
        } catch (EntityNotFoundException e) {
            throw new DataRecordingException(String.format("Entity with id = %s does not exist", chatDto.getUserId()));
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        if (!chatRepository.existsById(id)) {
            return false;
        }

        userChatRepository.deleteByChatId(id);
        return true;
    }

    @Override
    @Transactional
    public ChatWithUsersReadDto addUser(Long userId, Long chatId) throws EntityNotFoundException {
        var user = userRepository.findById(userId);
        var chat = chatRepository.findById(chatId);

        if (user.isEmpty())
            throw new EntityNotFoundException();

        if (chat.isEmpty())
            throw new EntityNotFoundException();

        var userChat = new UserChat(user.get(), chat.get());
        userChatRepository.save(userChat);

        return chatEntityToReadWithUsersDto.map(chat.get());
    }

    @Override
    @Transactional
    public ChatWithUsersReadDto removeUser(Long userId, Long chatId) throws EntityNotFoundException {
        var user = userRepository.findById(userId);
        var chat = chatRepository.findById(chatId);

        if (user.isEmpty())
            throw new EntityNotFoundException();

        if (chat.isEmpty())
            throw new EntityNotFoundException();

        userChatRepository.deleteByUserIdAndChatId(userId, chatId);

        return chatEntityToReadWithUsersDto.map(chat.get());
    }
}
