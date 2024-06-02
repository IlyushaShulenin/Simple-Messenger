package ru.shulenin.simple_messanger.service;

import java.util.List;
import java.util.Optional;
import ru.shulenin.simple_messanger.dto.MessageReadDto;
import ru.shulenin.simple_messanger.dto.MessageSaveDto;
import ru.shulenin.simple_messanger.exception.DataFetchingException;
import ru.shulenin.simple_messanger.exception.DataRecordingException;

public interface MessageService {
    List<MessageReadDto> findAll(Long chatId) throws DataFetchingException;
    Optional<MessageReadDto> findById(Long id);
    MessageReadDto save(MessageSaveDto messageDto) throws DataRecordingException;
    boolean delete(Long id);
}
