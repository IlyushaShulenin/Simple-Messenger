package ru.shulenin.simple_messanger.dto;

import lombok.Value;

@Value
public class UserEditDto {
    String email;
    String password;
    String login;
    String name;
    String surname;
}
