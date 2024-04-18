package ru.shulenin.simple_messanger.mapper;

import org.springframework.stereotype.Component;
import ru.shulenin.simple_messanger.dto.UserSaveDto;
import ru.shulenin.simple_messanger.entity.User;

@Component
public class UserSaveDtoToEntity implements Mapper<UserSaveDto, User> {
    @Override
    public User map(UserSaveDto from) {
        return new User(
                from.getEmail(),
                from.getPassword(),
                from.getLogin(),
                from.getName(),
                from.getSurname()
        );
    }
}
