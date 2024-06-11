package ru.shulenin.music_player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shulenin.music_player.entity.MusicUser;

public interface MusicUserRepository extends JpaRepository<MusicUser, Long> {
}
