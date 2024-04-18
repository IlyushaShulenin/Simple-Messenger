package ru.shulenin.simple_messanger.mapper;

public interface Mapper<F, T> {
    T map(F from);
}
