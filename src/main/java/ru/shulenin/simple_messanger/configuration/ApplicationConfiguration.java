package ru.shulenin.simple_messanger.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shulenin.simple_messanger.mapper.UserEntityToReadDto;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public UserEntityToReadDto userEntityToReadDto() {
        return new UserEntityToReadDto();
    }

}
