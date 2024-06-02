package ru.shulenin.simple_messanger.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.simple_messanger.dto.MessageReadDto;
import ru.shulenin.simple_messanger.dto.MessageSaveDto;
import ru.shulenin.simple_messanger.exception.DataFetchingException;
import ru.shulenin.simple_messanger.exception.DataRecordingException;
import ru.shulenin.simple_messanger.mapper.MessageEntityToReadDto;
import ru.shulenin.simple_messanger.mapper.MessageSaveDtoToEntity;
import ru.shulenin.simple_messanger.repository.jpa.ChatRepository;
import ru.shulenin.simple_messanger.repository.jpa.MessageRepository;
import ru.shulenin.simple_messanger.service.MessageService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    private final MessageEntityToReadDto messageEntityToReadDto;
    private final MessageSaveDtoToEntity messageSaveDtoToEntity;
    private final ChatRepository chatRepository;

    @Override
    public List<MessageReadDto> findAll(Long chatId) throws DataFetchingException {
        try {
            return messageRepository.findAllByChatId(chatId)
                    .stream()
                    .map(messageEntityToReadDto::map)
                    .toList();
        } catch (EntityNotFoundException e) {
            throw new DataFetchingException();
        }
    }

    @Override
    public Optional<MessageReadDto> findById(Long id) {
        return messageRepository.findById(id)
                .map(messageEntityToReadDto::map);
    }


    @Override
    @Transactional
    public MessageReadDto save(MessageSaveDto messageDto) throws DataRecordingException {
        try {
            var message = messageSaveDtoToEntity.map(messageDto);
            messageRepository.save(message);

            return messageEntityToReadDto.map(message);
        }
        catch (EntityNotFoundException e) {
            throw new DataRecordingException("");
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!messageRepository.existsById(id)) {
            return false;
        }

        chatRepository.deleteById(id);
        return true;
    }
}
