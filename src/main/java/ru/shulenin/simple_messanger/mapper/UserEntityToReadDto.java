package ru.shulenin.simple_messanger.mapper;

import org.springframework.stereotype.Component;
import ru.shulenin.simple_messanger.dto.UserReadDto;
import ru.shulenin.simple_messanger.entity.User;

@Component
public class UserEntityToReadDto implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User from) {
        return new UserReadDto(
                from.getId(),
                from.getLogin(),
                from.getName(),
                from.getSurname()
        );
    }
}
