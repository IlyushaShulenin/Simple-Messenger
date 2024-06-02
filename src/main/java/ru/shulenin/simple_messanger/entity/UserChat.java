package ru.shulenin.simple_messanger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Table(name = "user_chat")
@Data
@EqualsAndHashCode(of = {"user", "chat"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Chat chat;

    public UserChat(User user, Chat chat) {
        this.user = user;
        this.chat = chat;

        if (chat.getUserChats() == null) {
            chat.setUserChats(new ArrayList<>());
        }
        if (user.getUserChats() == null) {
            user.setUserChats(new ArrayList<>());
        }

        chat.getUserChats().add(this);
        user.getUserChats().add(this);
    }
}
