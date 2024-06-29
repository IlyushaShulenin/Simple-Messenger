package ru.shulenin.music_player.mapper;

import org.springframework.stereotype.Component;
import ru.shulenin.music_player.dto.MusicUserSaveDto;
import ru.shulenin.music_player.entity.MusicUser;

@Component
public class MusicUserSaveDtoToEntity implements Mapper<MusicUserSaveDto, MusicUser> {
    @Override
    public MusicUser map(MusicUserSaveDto from) {
        return new MusicUser(
                from.getEmail(),
                from.getPassword(),
                MusicUser.Role.LISTENER
        );
    }
}
