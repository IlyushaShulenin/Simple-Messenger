package ru.shulenin.simple_messanger.service;

import jakarta.persistence.EntityNotFoundException;
import ru.shulenin.simple_messanger.dto.ChatReadDto;
import ru.shulenin.simple_messanger.dto.ChatWithMessagesReadDto;
import ru.shulenin.simple_messanger.dto.ChatWithUsersReadDto;
import ru.shulenin.simple_messanger.dto.ChatSaveDto;
import ru.shulenin.simple_messanger.exception.DataRecordingException;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    List<ChatReadDto> findAll();
    Optional<ChatReadDto> findById(Long id);
    Optional<ChatWithUsersReadDto> findByIdWithUsers(Long id);
    Optional<ChatWithMessagesReadDto> findByIdWithMessages(Long id);
    ChatReadDto save(ChatSaveDto chatDto) throws DataRecordingException;
    boolean deleteById(Long id);
    ChatWithUsersReadDto addUser(Long userId, Long chatId) throws EntityNotFoundException;
    ChatWithUsersReadDto removeUser(Long userId, Long chatId) throws EntityNotFoundException;
}
