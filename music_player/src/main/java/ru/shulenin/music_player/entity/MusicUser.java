package ru.shulenin.music_player.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Builder
@ToString(exclude = {"playlists"})
@EqualsAndHashCode(exclude = {"id", "playlists"})
@AllArgsConstructor
@NoArgsConstructor
public class MusicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "playlist_music",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    @Builder.Default
    private List<Playlist> playlists = new ArrayList<>();

    public MusicUser(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public enum Role {
        LISTENER, CREATOR
    }
}
