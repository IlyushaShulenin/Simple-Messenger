package ru.shulenin.music_player.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private MusicUser creator;

    private byte[] data;

    public Music(String name, MusicUser creator, byte[] data) {
        this.name = name;
        this.creator = creator;
        this.data = data;
    }
}
