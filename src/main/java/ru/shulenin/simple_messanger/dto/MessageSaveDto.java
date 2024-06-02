package ru.shulenin.simple_messanger.dto;

import lombok.Value;

@Value
public class MessageSaveDto {
    Long senderId;
    Long chatId;
    String content;
}
