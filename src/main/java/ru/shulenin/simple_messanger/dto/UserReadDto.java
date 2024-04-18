package ru.shulenin.simple_messanger.dto;

import lombok.Value;

@Value
public class UserReadDto {
    Long id;
    String login;
    String name;
    String surname;
}
