package ru.shulenin.simple_messanger.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NamedEntityGraph(
        name = "User.userChats",
        attributeNodes = {@NamedAttributeNode("userChats")}
)
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"userChats", "messages"})
@EqualsAndHashCode(of = {"login"})
public class User implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 128)
    private String email;

    @Column(length = 128)
    private String password;

    @Column(unique = true, length = 64)
    private String login;

    @Column(length = 64)
    private String name;

    @Column(length = 64)
    private String surname;

    @Enumerated(value = EnumType.STRING)
    private Authority isActive;

//    @ManyToMany(
//            fetch = FetchType.LAZY
//    )
//    @JoinTable(
//            name = "user_chat",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "chat_id")
//    )
//    //@OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Chat> chats = new ArrayList<>();


    @OneToMany
    @JoinColumn(name = "user_id")
    private List<UserChat> userChats = new ArrayList<>();

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "sender_id")
    private Set<Message> messages = new HashSet<>();

    public User(String email, String password, String login, String name, String surname) {
        this.email = email;
        this.password = password;
        this.login = login;
        this.name = name;
        this.surname = surname;
    }



//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.emptyList();
//    }

//    @Override
//    public String getUsername() {
//        return login;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

//    public void addChat(Chat chat) {
//        chats.add(chat);
//        chat.getUsers().add(this);
//
//    }
}
