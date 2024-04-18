package ru.shulenin.simple_messanger.service;

import ru.shulenin.simple_messanger.dto.UserReadDto;
import ru.shulenin.simple_messanger.dto.UserSaveDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserReadDto> findAll();
    Optional<UserReadDto> findById(Long id);
    UserReadDto saveUser(UserSaveDto userDto);
    boolean deleteUser(Long id);
    Optional<UserReadDto> updateUser(Long id, UserSaveDto userDto);
}
