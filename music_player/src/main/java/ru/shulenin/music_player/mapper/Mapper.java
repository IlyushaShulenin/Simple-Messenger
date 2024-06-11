package ru.shulenin.music_player.mapper;

public interface Mapper<F, T> {
    T map(F from);
}
