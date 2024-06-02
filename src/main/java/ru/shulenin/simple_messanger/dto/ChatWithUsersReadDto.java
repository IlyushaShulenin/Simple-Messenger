package ru.shulenin.simple_messanger.dto;

import lombok.Value;

import java.util.List;

@Value
public class ChatWithUsersReadDto {
    String name;
    List<String> chatMembers;
}
