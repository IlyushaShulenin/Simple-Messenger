package ru.shulenin.music_player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.shulenin.music_player.entity.Music;
import ru.shulenin.music_player.service.FileDownLoadService;
import ru.shulenin.music_player.service.FileUploadService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/music")
public class MusicController {
    private final FileUploadService fileUploadService;
    private final FileDownLoadService fileDownLoadService;

//    @PostMapping(value = "/form", consumes = "multipart/form-data")
//    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
//        fileUploadService.saveFile(file);
//        return "success";
//    }
//
//    @GetMapping("/{name}")
//    public Music download(@PathVariable("name") String name) {
//        return fileDownLoadService.download(name).get();
//    }

//    @PostMapping(value = "/form")
//    public String save(@RequestParam("file") MultipartFile file) throws IOException {
//        fileUploadService.saveFile(file);
//        return "redirect:/music/form";
//    }

}