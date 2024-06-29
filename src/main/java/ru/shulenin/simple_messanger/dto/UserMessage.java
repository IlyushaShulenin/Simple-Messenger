package ru.shulenin.simple_messanger.dto;

import lombok.Value;

@Value
public class UserMessage {
    String email;
    String password;
}
