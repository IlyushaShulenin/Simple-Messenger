package ru.shulenin.simple_messanger.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.simple_messanger.dto.UserReadDto;
import ru.shulenin.simple_messanger.dto.UserSaveDto;
import ru.shulenin.simple_messanger.mapper.UserEntityToReadDto;
import ru.shulenin.simple_messanger.mapper.UserSaveDtoToEntity;
import ru.shulenin.simple_messanger.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserEntityToReadDto userEntityToReadDto;
    private final UserSaveDtoToEntity userSaveDtoToEntity;

    @Override
    public List<UserReadDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userEntityToReadDto::map)
                .toList();
    }

    @Override
    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userEntityToReadDto::map);
    }

    @Override
    @Transactional
    public UserReadDto saveUser(UserSaveDto userDto) {
        var user = userSaveDtoToEntity.map(userDto);
        return userEntityToReadDto.map(userRepository.save(user));
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) throws EntityNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        return deleteUser(id);
    }

    @Override
    @Transactional
    public Optional<UserReadDto> updateUser(Long id, UserSaveDto userDto) throws EntityNotFoundException {
        var user = userRepository.findById(id);
        return user.map(usr -> {
            usr.setEmail(userDto.getEmail());
            usr.setPassword(userDto.getPassword());
            usr.setLogin(userDto.getLogin());
            usr.setName(userDto.getName());
            usr.setSurname(userDto.getSurname());

            return userEntityToReadDto.map(usr);
        });
    }
}
