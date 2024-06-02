package ru.shulenin.simple_messanger.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.shulenin.simple_messanger.dto.MessageReadDto;
import ru.shulenin.simple_messanger.dto.MessageSaveDto;
import ru.shulenin.simple_messanger.exception.DataFetchingException;
import ru.shulenin.simple_messanger.exception.DataRecordingException;
import ru.shulenin.simple_messanger.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("${app.name}/messages")
@RequiredArgsConstructor
public class MessageRestController {
    private final MessageService messageService;

    @GetMapping
    public List<MessageReadDto> findAll(@RequestParam("id") Long chatId) {
        try {
            return messageService.findAll(chatId);
        } catch (DataFetchingException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public MessageReadDto save(@RequestBody MessageSaveDto messageDto, Authentication authentication) {
        try {
            return messageService.save(messageDto);
        } catch (DataRecordingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
