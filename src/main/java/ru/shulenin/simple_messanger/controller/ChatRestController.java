package ru.shulenin.simple_messanger.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.shulenin.simple_messanger.dto.ChatReadDto;
import ru.shulenin.simple_messanger.dto.ChatWithUsersReadDto;
import ru.shulenin.simple_messanger.dto.ChatSaveDto;
import ru.shulenin.simple_messanger.exception.DataRecordingException;
import ru.shulenin.simple_messanger.service.ChatService;

@RestController
@RequestMapping("${app.name}/chats")
@RequiredArgsConstructor
public class ChatRestController {
    private final ChatService chatService;

    @GetMapping
    public ChatReadDto findById(@RequestParam("id") Long id) {
        return chatService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ChatReadDto create(@RequestBody ChatSaveDto chatDto) {
        try {
            return chatService.save(chatDto);
        } catch (DataRecordingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable("id") Long id) {
//        if (!chatService.deleteById(id)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ChatWithUsersReadDto addUser(@PathVariable("id") Long id, @RequestParam("userId") Long userId) {
        try {
            return chatService.addUser(userId, id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ChatWithUsersReadDto deleteUser(@PathVariable("id") Long id, @RequestParam("userId") Long userId) {
        try {
            return chatService.removeUser(userId, id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
