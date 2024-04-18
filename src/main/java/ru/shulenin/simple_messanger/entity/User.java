package ru.shulenin.simple_messanger.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
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

    private Boolean isActive;

    public User(String email, String password, String login, String name, String surname) {
        this.email = email;
        this.password = password;
        this.login = login;
        this.name = name;
        this.surname = surname;
    }
}
