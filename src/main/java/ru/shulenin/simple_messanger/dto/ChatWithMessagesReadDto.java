package ru.shulenin.simple_messanger.dto;

import lombok.Value;

import java.util.List;

@Value
public class ChatWithMessagesReadDto {
    String name;
    List<String> messages;
}
