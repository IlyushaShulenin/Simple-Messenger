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
public class Message implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    private LocalDateTime sendTime;

    private String content;

    public Message(User user, Chat chat, LocalDateTime sendTime, String content) {
        this.user = user;
        this.chat = chat;
        this.sendTime = sendTime;
        this.content = content;
    }
}
