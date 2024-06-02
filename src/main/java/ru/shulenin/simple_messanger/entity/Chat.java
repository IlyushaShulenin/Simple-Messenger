package ru.shulenin.simple_messanger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;


@NamedEntityGraph(
        name = "Chat.userChats",
        attributeNodes = {
                @NamedAttributeNode(value = "userChats", subgraph = "UserChat.user-chat"),
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "UserChat.user-chat",
                        attributeNodes = {
                                @NamedAttributeNode("user"),
                                @NamedAttributeNode("chat")
                        }
                )
        }
)
@NamedEntityGraph(
        name = "Chat.messages",
        attributeNodes = {@NamedAttributeNode(value = "messages", subgraph = "Message.user")},
        subgraphs = {
                @NamedSubgraph(
                        name = "Message.user",
                        attributeNodes = @NamedAttributeNode("user")
                )
        }
)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
@ToString(exclude = {"userChats", "messages"})
@Builder
public class Chat implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany
//    @JoinTable(
//            name = "user_chat",
//            joinColumns = @JoinColumn(name = "chat_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private List<User> users = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    @Builder.Default
    private List<UserChat> userChats = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    @Builder.Default
    private Set<Message> messages = new HashSet<>();

    public Chat(String name) {
        this.name = name;
    }
}
