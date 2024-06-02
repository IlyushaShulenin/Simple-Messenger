package ru.shulenin.simple_messanger.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class MessageReadDto {
    String senderLogin;
    String chatName;
    LocalDateTime sendTime;
    String content;
}
