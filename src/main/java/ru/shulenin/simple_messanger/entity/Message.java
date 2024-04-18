package ru.shulenin.simple_messanger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime sendTime;

    private String content;

    public Message(LocalDateTime sendTime, String content) {
        this.sendTime = sendTime;
        this.content = content;
    }
}
