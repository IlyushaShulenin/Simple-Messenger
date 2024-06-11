package ru.shulenin.music_player.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class FileDto {
    MultipartFile file;
}
