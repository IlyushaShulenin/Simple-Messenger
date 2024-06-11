package ru.shulenin.music_player.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    @OneToMany(mappedBy = "user")
    private List<Playlist> playlists = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "playlist_document",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private List<Music> musics = new ArrayList<>();

    public MusicUser(String email, String password, Role role, List<Playlist> playlists) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.playlists = playlists;
    }

    public enum Role {
        LISTENER, CREATOR
    }
}
