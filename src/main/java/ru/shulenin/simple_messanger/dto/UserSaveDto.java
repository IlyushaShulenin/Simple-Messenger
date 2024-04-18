package ru.shulenin.simple_messanger.dto;

import lombok.Value;

@Value
public class UserSaveDto {
    String email;
    String password;
    String login;
    String name;
    String surname;
}
