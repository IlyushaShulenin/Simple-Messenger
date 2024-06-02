package ru.shulenin.simple_messanger.dto;

import lombok.Value;

@Value
public class ChatSaveDto {
    Long userId;
    String name;
}
