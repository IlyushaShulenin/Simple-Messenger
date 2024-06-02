package ru.shulenin.simple_messanger.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.shulenin.simple_messanger.dto.UserReadDto;
import ru.shulenin.simple_messanger.dto.UserSaveDto;
import ru.shulenin.simple_messanger.exception.DataRecordingException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<UserReadDto> findAll();
    Optional<UserReadDto> findById(Long id);
    UserReadDto saveUser(UserSaveDto userDto) throws DataRecordingException;
    boolean deleteUser(Long id);
    Optional<UserReadDto> updateUser(Long id, UserSaveDto userDto);
}
