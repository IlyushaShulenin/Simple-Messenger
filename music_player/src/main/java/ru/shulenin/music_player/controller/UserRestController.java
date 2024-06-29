package ru.shulenin.music_player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shulenin.music_player.dto.MusicUserReadDto;
import ru.shulenin.music_player.dto.MusicUserSaveDto;
import ru.shulenin.music_player.service.MusicUserService;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final MusicUserService userService;

    @PostMapping
    public MusicUserReadDto save(@RequestBody MusicUserSaveDto userSaveDto) {
        System.out.println("Received!");
        return userService.save(userSaveDto);
    }

}
