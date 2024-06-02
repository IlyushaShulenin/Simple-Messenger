package ru.shulenin.simple_messanger.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shulenin.simple_messanger.dto.UserReadDto;
import ru.shulenin.simple_messanger.dto.UserSaveDto;
import ru.shulenin.simple_messanger.exception.DataRecordingException;
import ru.shulenin.simple_messanger.mapper.UserEntityToReadDto;
import ru.shulenin.simple_messanger.mapper.UserSaveDtoToEntity;
import ru.shulenin.simple_messanger.repository.jpa.UserRepository;
import ru.shulenin.simple_messanger.repository.redis.UserRedisRepository;
import ru.shulenin.simple_messanger.security.UserDetailsDto;
import ru.shulenin.simple_messanger.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRedisRepository userRedisRepository;

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
        var user = userRepository.findById(id)
                .map(userEntityToReadDto::map);

        user.ifPresent(userReadDto ->
                userRedisRepository.save(userReadDto.getLogin())
        );

        return user;
    }

    @Override
    @Transactional
    public UserReadDto saveUser(UserSaveDto userDto) throws DataRecordingException {
        var login = userDto.getLogin();

        if (userRedisRepository.existsById(login)) {
            throw new DataRecordingException("");
        }
        else {
            var user = userRepository.findByLogin(login);

            if (user.isPresent()) {
                userRedisRepository.save(login);
                throw new DataRecordingException("");
            }
        }

        userRedisRepository.save(login);
        var user = userSaveDtoToEntity.map(userDto);
        return userEntityToReadDto.map(userRepository.saveAndFlush(user));
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) throws EntityNotFoundException {
        if (!userRepository.existsById(id)) {
            return false;
        }

        userRepository.deleteById(id);
        return true;
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

    @Override
    public UserDetailsDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(UserDetailsDto::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
